package com.hotelreservation.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelreservation.demo.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation , Integer>{
  public Optional<Reservation> findByRoomNumber(int roomNumber);

  List<Reservation> findByGuestNameContainingIgnoreCase(String guestName);

}
