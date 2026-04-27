package com.hotelreservation.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelreservation.demo.entity.Reservation;
import com.hotelreservation.demo.exception.DuplicateRoomException;
import com.hotelreservation.demo.exception.noReservationException;
import com.hotelreservation.demo.repository.ReservationRepository;

@Service
public class ReservationService {
  @Autowired
    private ReservationRepository repository;

    public Reservation createReservation(Reservation reservation) {

        //Check if room already booked
        Optional<Reservation> existing = repository.findByRoomNumber(reservation.getRoomNumber());

        if (existing.isPresent()) {
            throw new DuplicateRoomException("Room already booked!");
        }

        //Set booking time (important business logic)
        reservation.setBookingTime(LocalDateTime.now());

        //Save to DB (this replaces JDBC insert)
        return repository.save(reservation);
    }

    public List<Reservation> getAllReservations(){
      List<Reservation> allReservations = repository.findAll();
      if(allReservations.isEmpty()){
        throw new noReservationException("No reservations available!!");
      }
      return allReservations;
    }

    public Reservation getById(int id){
      // Optional<Reservation> reservationExists = repository.findById(id);

      return repository.findById(id).orElseThrow(() -> new noReservationException("Reservation for "+ id +" does not Exist."));
    }

    public Reservation getByRoomNumber(int roomNumber){
      return repository.findByRoomNumber(roomNumber).orElseThrow(() -> new noReservationException("No Reservation Exists for Room No."+roomNumber));
    }

    public Optional<Reservation> findByRoomNumber(int roomNumber){
      return repository.findByRoomNumber(roomNumber);
    }

    public List<Reservation> searchByName(String guestName){
    List<Reservation> results = repository.findByGuestNameContainingIgnoreCase(guestName);

    if(results.isEmpty()){
        throw new noReservationException("Reservation does not exist for " + guestName);
    }

      return results;
    }

    public Reservation updateReservation(Reservation reservation) {

    Reservation existing = repository.findById(reservation.getReservation_id())
        .orElseThrow(() -> new noReservationException("Reservation not found"));

    // update only fields
    existing.setGuestName(reservation.getGuestName());
    existing.setRoomNumber(reservation.getRoomNumber());
    existing.setContactNumber(reservation.getContactNumber());

    if (existing.getReservationDate() == null) {
    existing.setReservationDate(LocalDate.now());
}

    return repository.save(existing);
}
    public String deleteReservation(int id){
      Optional<Reservation> reservationExists = repository.findById(id);
      if(reservationExists.isPresent()){
        repository.deleteById(id);
        return "Reservation deleted successfully";
      } 
      throw new noReservationException("Reservation with "+id+" does not exist");
    }

    public String checkAvailability(int roomNumber){
      Optional<Reservation> reservation = repository.findByRoomNumber(roomNumber);
      if(reservation.isPresent()){
        return "Occupied";
      }
      return "Available";
    }
}
