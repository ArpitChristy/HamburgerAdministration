package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @GetMapping("/reservations")
    public ResponseEntity<?> getAllReservations(@RequestParam(required=false) String name){
        try{
            List<Reservation> reservations = new ArrayList<>();
            if(name == null || name.length() == 0){
                log.info("String variable name is empty");
                reservationRepository.findAll().forEach(reservations::add);
            }
            else{
                reservationRepository.findByFullNameContaining(name).forEach(reservations::add);
            }
            if(reservations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> getByReservationId(@PathVariable("id") int reservationId){
        Optional<Reservation> reservationData  = reservationRepository.findByReservationId(reservationId);
        if(reservationData.isPresent()){
            return new ResponseEntity<>(reservationData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/reservations")
    public ResponseEntity<?> saveReservations(@RequestBody Reservation reservation) {
        try {
            reservationRepository.save(reservation);
            return new ResponseEntity<>("Created a new Reservation successfully!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("reservations/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable("id") int reservationId, @RequestBody Reservation reservation){
        Optional<Reservation> reservationData = reservationRepository.findByReservationId(reservationId);
        try {
            if (reservationData.isPresent()) {
                Reservation reservation_obj = reservationData.get();
                reservation_obj.setFullName(reservation.getFullName());
                reservation_obj.setLocationId(reservation.getLocationId());
                reservation_obj.setPhone(reservation.getPhone());
                reservation_obj.setEmail(reservation.getEmail());
                reservation_obj.setDateOfBooking(reservation.getDateOfBooking());
                reservation_obj.setDateOfEvent(reservation.getDateOfEvent());
                reservation_obj.setStatus(reservation.getStatus());

                return new ResponseEntity<>(reservationRepository.save(reservation_obj), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Failed to update the reservation!",HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") int reservationId){
        try{
            reservationRepository.deleteById(reservationId);
            return new ResponseEntity<>("Successfully deleted the reservation",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/reservations")
    public ResponseEntity<?> deleteAllReservations(){
        try{
            reservationRepository.deleteAll();
            return new ResponseEntity<>("Deleted all reservations successfully",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
