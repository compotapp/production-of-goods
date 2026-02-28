package com.pot.app.productionofgoods.repository;

import com.pot.app.productionofgoods.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
