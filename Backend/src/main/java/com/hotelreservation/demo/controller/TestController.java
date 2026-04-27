package com.hotelreservation.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelreservation.demo.entity.Reservation;
import com.hotelreservation.demo.repository.ReservationRepository;

@RestController
public class TestController {
  @Autowired
  private ReservationRepository repository;

  @GetMapping("/test")
  public List<Reservation> test() {
    return repository.findAll();
  }
}
