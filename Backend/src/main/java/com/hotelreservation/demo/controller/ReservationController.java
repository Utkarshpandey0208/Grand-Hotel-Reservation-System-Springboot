package com.hotelreservation.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelreservation.demo.entity.Reservation;
import com.hotelreservation.demo.service.ReservationService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReservationController {
  @Autowired
  private ReservationService reservationService;
  @PostMapping("/reservations")
  public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
    return ResponseEntity.status(201).body(reservationService.createReservation(reservation));
  }

  @GetMapping("/reservations")
  public ResponseEntity<List<Reservation>> getAllReservations(){
    return ResponseEntity.ok(reservationService.getAllReservations());
  }

 @GetMapping("/reservations/availability/{roomNumber}")
public ResponseEntity<?> checkAvailability(@PathVariable int roomNumber) {

    Optional<Reservation> reservation = reservationService.findByRoomNumber(roomNumber);

    if (reservation.isPresent()) {
        return ResponseEntity.ok(Map.of(
                "status", "OCCUPIED",
                "reservation", reservation.get()
        ));
    } else {
        return ResponseEntity.ok(Map.of(
                "status", "AVAILABLE"
        ));
    }
}

  @GetMapping("/reservations/{id}")
  public ResponseEntity<Reservation> getById(@PathVariable int id){
    return ResponseEntity.ok(reservationService.getById(id));
  }

  @GetMapping("/reservations/search")
  public ResponseEntity<List<Reservation>> searchByName(@RequestParam String guestName){
    return ResponseEntity.ok(reservationService.searchByName(guestName));
  }

  @GetMapping("/reservations/room/{roomNumber}")
  public ResponseEntity<Reservation> getByRoomNumber(@PathVariable int roomNumber){
    return ResponseEntity.ok(reservationService.getByRoomNumber(roomNumber));
  }

  @PutMapping("/reservations/{id}")
  public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @RequestBody Reservation reservation){
    reservation.setReservation_id(id);
    return ResponseEntity.ok(reservationService.updateReservation(reservation));
  }

  @DeleteMapping("/reservations/{id}")
  public ResponseEntity<String> deleteReservation(@PathVariable int id){
    return ResponseEntity.ok(reservationService.deleteReservation(id));
  }


}
