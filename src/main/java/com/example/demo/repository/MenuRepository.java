package com.example.demo.repository;

import com.example.demo.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends MongoRepository<Menu,Integer> {
    List<Menu> findByItemContaining(String name);
    Optional<Menu> findByMenuId(int id);
    List<Menu> findByCategoryContaining(String name);
}
