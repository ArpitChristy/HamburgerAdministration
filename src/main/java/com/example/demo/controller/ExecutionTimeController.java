package com.example.demo.controller;

import com.example.demo.model.ExecutionTime;
import com.example.demo.repository.ExecutionTimeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api")
public class ExecutionTimeController {
    @Autowired
    ExecutionTimeRepository executionTimeRepository;

    @GetMapping("/time/{requestURI}")
    public ResponseEntity<?> getExecutionTime(@PathVariable("requestURI") String requestURI){
        log.info("the url to search is " + requestURI);
        log.info("ExecutionTimeController --getExecutionTime");
        List<ExecutionTime> executionData = new ArrayList<>();
        executionTimeRepository.findByRequestURI(requestURI).forEach(executionData::add);
        if(!executionData.isEmpty()){
            return new ResponseEntity<>(executionData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}