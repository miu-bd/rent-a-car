package com.miu.swe.service;

import com.miu.swe.model.Car;
import com.miu.swe.model.Station;
import com.miu.swe.repository.CarRepository;
import com.miu.swe.repository.RentalRepository;
import com.miu.swe.util.MessagesBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarService {

    private MessagesBean messages;
    private CarRepository carRepository;
    private StationService stationService;
    private RentalRepository rentalRepository;


    public CarService(MessagesBean messages, CarRepository carRepository, StationService stationService, RentalRepository rentalRepository) {
        this.messages = messages;
        this.carRepository = carRepository;
        this.stationService = stationService;
        this.rentalRepository = rentalRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findByStation(Station station) {
        return carRepository.findByStation(station);
    }

    public List<Car> findByMileageGreaterThan(Integer mileage) {
        return carRepository.findByMileageGreaterThan(mileage);
    }

    public Car create(Car car) {
        if (car.getStation() == null) {
            throw new IllegalArgumentException(messages.get("carStationNotNull"));
        }
        if (car.getStation().getId() == null || !stationService.existsById(car.getStation().getId())) {
            throw new EntityNotFoundException(messages.get("stationNotFound"));
        }
        if (carRepository.existsById(car.getRegistrationNr())) {
            throw new EntityExistsException(messages.get("carAlreadyExists"));
        }
        return carRepository.save(car);
    }

    public void deleteById(String registrationNr) throws Exception{
        Car car = carRepository.findById(registrationNr)
                .orElseThrow(() -> new EntityNotFoundException(messages.get("carNotFound")));
        if (!canDelete(car)) {
            throw new IllegalArgumentException(messages.get("carDeleteError"));
        }
        carRepository.delete(car);
    }

    public boolean canDelete(Car car) {
        return rentalRepository.findByCar(car).isEmpty();
    }

}
