package com.hotelreservation.demo.exception;

public class noReservationException extends RuntimeException {
  public noReservationException(String message){
    super(message);
  }
}
