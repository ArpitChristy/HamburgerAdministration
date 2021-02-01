package com.example.demo.controller;

import com.example.demo.model.OpenHours;
import com.example.demo.repository.OpenHoursRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping("/api")
public class OpenHoursController {
    @Autowired
    OpenHoursRepository openHoursRepository;

    @GetMapping("/openhours")
    public ResponseEntity<?> getAllOpenHours(@RequestParam(required=false) String name){
        try{
            List<OpenHours> openHours = new ArrayList<>();
            if(name == null || name.length() == 0){
                log.info("String variable name is empty");
                openHoursRepository.findAll().forEach(openHours::add);
            }
            else{
                openHoursRepository.findByDayContaining(name).forEach(openHours::add);
            }
            if(openHours.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(openHours, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/openhours/{id}")
    public ResponseEntity<?> getOpenHoursById(@PathVariable("id") int openHoursId){
        Optional<OpenHours> openHoursData  = openHoursRepository.findByOpenHoursId(openHoursId);
        if(openHoursData.isPresent()){
            return new ResponseEntity<>(openHoursData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/openhours")
    public ResponseEntity<?> saveOpenHours(@RequestBody OpenHours openHours) {
        try {
            openHoursRepository.save(openHours);
            return new ResponseEntity<>("Created a new Location successfully!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("openhours/{id}")
    public ResponseEntity<?> updateOpenHours(@PathVariable("id") int openHoursId, @RequestBody OpenHours openHours){
        Optional<OpenHours> openHoursData = openHoursRepository.findByOpenHoursId(openHoursId);
        if(openHoursData.isPresent()){
            OpenHours openHours_obj = openHoursData.get();
            openHours_obj.setOpenTime(openHours.getOpenTime());
            openHours_obj.setDay(openHours.getDay());
            openHours_obj.setCloseTime(openHours.getCloseTime());

            return new ResponseEntity<>(openHoursRepository.save(openHours_obj), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("openhours/{id}")
    public ResponseEntity<?> deleteOpenHours(@PathVariable("id") int openHoursId){
        try{
            openHoursRepository.deleteById(openHoursId);
            return new ResponseEntity<>("Successfully deleted the location",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/openhours")
    public ResponseEntity<?> deleteAllOpenHours(){
        try{
            openHoursRepository.deleteAll();
            return new ResponseEntity<>("Deleted all locations successfully",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
