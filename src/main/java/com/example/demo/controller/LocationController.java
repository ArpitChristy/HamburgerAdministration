package com.example.demo.controller;

import com.example.demo.model.Location;
import com.example.demo.repository.LocationRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    LocationRepository locationRepository;
    @GetMapping("/locations")
    public ResponseEntity<?> getAllLocations(@RequestParam(required=false) String name,@RequestParam(required=false,defaultValue = "0") Integer pageNo,@RequestParam(required=false,defaultValue = "10") Integer pageSize){
        try{
            List<Location> locations = new ArrayList<>();
            if(name == null || name.length() == 0){
                log.info("String variable name is empty finding all values");
                //locationRepository.findAll().forEach(locations::add);
                Pageable pageable = PageRequest.of(pageNo,pageSize);
                Page<Location> page = locationRepository.findAll(pageable);
                return new ResponseEntity<>(page.getContent(),HttpStatus.OK);
            }
            else{
                locationRepository.findByNameContaining(name).forEach(locations::add);
            }
            if(locations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/locations/{id}")
    public ResponseEntity<?> getByLocationId(@PathVariable("id") int locationId){
        Optional<Location> locationData  = locationRepository.findByLocationId(locationId);
        if(locationData.isPresent()){
            return new ResponseEntity<>(locationData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/locations")
    public ResponseEntity<?> saveLocations(@RequestBody Location location) {
        try {
            locationRepository.save(location);
            return new ResponseEntity<>("Created a new Location successfully!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("locations/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") int locationId, @RequestBody Location location){
        Optional<Location> locationData = locationRepository.findByLocationId(locationId);
        if(locationData.isPresent()){
            Location location_obj = locationData.get();
            location_obj.setName(location.getName());
            location_obj.setLatitude(location.getLatitude());
            location_obj.setLongitude(location.getLongitude());
            location_obj.setPhone(location.getPhone());
            location_obj.setAddress(location.getAddress());

            return new ResponseEntity<>(locationRepository.save(location_obj), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("locations/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable("id") int locationId){
        try{
            locationRepository.deleteById(locationId);
            return new ResponseEntity<>("Successfully deleted the location",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/locations")
    public ResponseEntity<?> deleteAllLocations(){
        try{
            locationRepository.deleteAll();
            return new ResponseEntity<>("Deleted all locations successfully",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
