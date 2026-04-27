package com.hotelreservation.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRoomException.class)
    public ResponseEntity<String> handleDuplicateRoom(DuplicateRoomException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(noReservationException.class)
    public ResponseEntity<String> handleNoReservation(noReservationException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    
}
