package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.entity.NumberSequence;
import com.pot.app.productionofgoods.entity.NumberSequence.SequenceType;
import com.pot.app.productionofgoods.repository.jpa.NumberSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class NumberGeneratorServiceImpl implements NumberGeneratorService {

    private final static String FORMAT = "%s-%04d";
    private final NumberSequenceRepository repository;

    @Override
    @Transactional
    public String generateNumber(SequenceType type) {
        NumberSequence sequence = findByType(type);
        Integer number = sequence.getNextNumber();
        save(sequence);
        return format(FORMAT, type.getPrefix(), number);
    }

    @Override
    @Transactional
    public List<String> generateNumbers(SequenceType type, Integer count) {
        NumberSequence sequence = findByType(type);
        List<Integer> numbers = sequence.getNextNumbers(count);
        save(sequence);
        return numbers.stream()
                .map(number -> format(FORMAT, type.getPrefix(), number))
                .toList();
    }

    private void save(NumberSequence sequence) {
        repository.save(sequence);
    }

    private NumberSequence findByType(SequenceType type) {
        return repository.findByTypeWithPessimisticLock(type)
                .orElseGet(() -> { //Если нет - создаем новую
                    return repository.save(new NumberSequence(type));
                });
    }
}
