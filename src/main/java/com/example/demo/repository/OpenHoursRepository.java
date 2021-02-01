package com.example.demo.repository;

import com.example.demo.model.OpenHours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpenHoursRepository extends MongoRepository<OpenHours,Integer> {
    List<OpenHours> findByDayContaining(String day);
    Optional<OpenHours> findByOpenHoursId(int openHoursId);
}
