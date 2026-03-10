package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.entity.NumberSequence;

import java.util.List;

public interface NumberGeneratorService {

    String generateNumber(NumberSequence.SequenceType type);

    List<String> generateNumbers(NumberSequence.SequenceType type, Integer count);
}
