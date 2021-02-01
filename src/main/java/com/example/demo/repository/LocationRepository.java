package com.example.demo.repository;

import com.example.demo.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LocationRepository extends MongoRepository<Location, Integer> {
    List<Location> findByNameContaining(String name);
    Optional<Location> findByLocationId(Integer id);
}
