package com.example.demo.repository;

import com.example.demo.entity.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CarRepository extends CrudRepository<Car,Long> {
    @Override
    ArrayList<Car> findAll();

    @Query("SELECT car FROM Car car WHERE car.history IS NOT NULL")
    ArrayList<Car> findCarWithHistory();

}
