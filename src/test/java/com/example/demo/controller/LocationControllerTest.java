package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LocationControllerTest {
    @Autowired
    LocationController locationController;

    @Test
    void getAllLocations() {
        ResponseEntity<?> response  = locationController.getAllLocations();

    }

    @Test
    void getByLocationId() {
    }

    @Test
    void saveLocations() {
    }

    @Test
    void updateLocation() {
    }

    @Test
    void deleteLocation() {
    }

    @Test
    void deleteAllLocations() {
    }
}