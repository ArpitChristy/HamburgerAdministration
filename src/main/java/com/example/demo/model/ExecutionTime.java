package com.example.demo.model;

import com.example.demo.repository.ExecutionTimeRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ExecutionTime {
    @Id
    private int id;
    private String requestURI;
    private long time;
    public ExecutionTime(){

    }
    public ExecutionTime(String requestURI, long executionTime) {
        this.requestURI = requestURI;
        this.time = executionTime;
    }
}
