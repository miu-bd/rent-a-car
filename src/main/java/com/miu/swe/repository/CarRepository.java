package com.miu.swe.repository;

import com.miu.swe.model.Car;
import com.miu.swe.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findByStation(Station station);

    List<Car> findByMileageGreaterThan(Integer mileage);
}
