package com.hotelreservation.demo.exception;

public class DuplicateRoomException extends RuntimeException{
  public DuplicateRoomException(String message){
    super(message);
  }
}