package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;
import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.Reservation;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;
import com.pot.app.productionofgoods.mapping.ReservationMapping;
import com.pot.app.productionofgoods.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.pot.app.productionofgoods.mapping.ReservationMapping.toResponse;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final GoodsService goodsService;

    public ReservationServiceImpl(ReservationRepository repository, GoodsService goodsService) {
        this.repository = repository;
        this.goodsService = goodsService;
    }

    @Override
    @Transactional
    public ReservationResponse reservation(ReservationRequest dto) {
        Set<String> names = ReservationMapping.getNames(dto);
        Set<Goods> goods = goodsService.findAllByNameIn(names);
        List<Reservation> reservations = repository.saveAll(ReservationMapping.toEntity(dto, goods));
        Set<UpdateResultGoods> updateResultGoods = goodsService.updateAll(
                ReservationMapping.getArrayNames(dto),
                ReservationMapping.getArrayQuantities(dto)
        );
        System.out.println();
        return toResponse(updateResultGoods, reservations);
    }

//    @Override
//    @Transactional
//    public void reservation(ReservationRequest dto) {
//        Map<String, Integer> reservationsMap = dto.reservationItemDtos().stream()
//                .collect(Collectors.toMap(ReservationItemDto::externalGoodsId, ReservationItemDto::quantity));
//        Set<Goods> goodsSet = goodsService.findByNameIn(reservationsMap.keySet());
//        goodsSet.forEach(goods -> goods.minusQuantity(reservationsMap.get(goods.getName())));
//        List<Goods> goodsList = goodsService.updateAll(goodsSet);
//        List<Reservation> reservations = repository.saveAll(toEntity(dto, goodsList));
//    }
}
