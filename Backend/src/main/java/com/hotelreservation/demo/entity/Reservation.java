package com.hotelreservation.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int reservation_id;

  @Column(name="guest_name")
  private String guestName; 

  @Column(name="room_Number")
  private int roomNumber;

  @Column(name="contact_number")
  private String contactNumber;

  @Column(name="booking_time")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime bookingTime;

  @Column(nullable = false)
  private LocalDate reservationDate;

  @PrePersist
public void beforeSave() {
    this.reservationDate = LocalDate.now();
    this.bookingTime = LocalDateTime.now();
}

@PreUpdate
public void beforeUpdate() {
    if (this.reservationDate == null) {
        this.reservationDate = LocalDate.now();
    }
}
  // public int getReservation_id() {
  //   return reservation_id;
  // }
  // public void setReservation_id(int reservation_id) {
  //   this.reservation_id = reservation_id;
  // }

}
