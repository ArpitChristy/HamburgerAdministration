package com.example.demo.repository;

import com.example.demo.model.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime,Integer> {
    List<ExecutionTime> findByRequestURI(String requestURI);
}
