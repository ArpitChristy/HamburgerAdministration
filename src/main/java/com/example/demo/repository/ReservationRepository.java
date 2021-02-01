package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,Integer> {
    List<Reservation> findByFullNameContaining(String name);
    Optional<Reservation> findByReservationId(int id);
}
