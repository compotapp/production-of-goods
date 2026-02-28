package com.pot.app.productionofgoods.repository;

import com.pot.app.productionofgoods.entity.Reservation;
import com.pot.app.productionofgoods.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Modifying
    @Query("UPDATE Reservation SET status = :newStatus WHERE number = :number AND status = :currentStatus")
    void updateStatus(String number, ReservationStatus currentStatus, ReservationStatus newStatus);

    @Modifying
    @Query("UPDATE Reservation SET status = :newStatus WHERE number = :number")
    void updateStatus(String number, ReservationStatus newStatus);
}
