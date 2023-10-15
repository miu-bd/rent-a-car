package com.miu.swe.service;

import com.miu.swe.bean.FinishRentalBean;
import com.miu.swe.model.Car;
import com.miu.swe.model.Rental;
import com.miu.swe.repository.RentalRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private CarService carService;

    public RentalService(RentalRepository rentalRepository, CarService carService) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
    }

    public void create(Rental rental) {
        rental.setId(null);
        rental.setKm(null);
        rental.setReturnDate(null);
        rental.setReturnStation(null);
        rental.getCar().setStation(null);

        rentalRepository.save(rental);
    }

    public void finish(Rental rental, FinishRentalBean finishRentalBean) {
        rental.setReturnStation(finishRentalBean.getReturnStation());
        rental.setKm(finishRentalBean.getKm());
        rental.getCar().setStation(rental.getReturnStation());
        rental.getCar().setMileage(rental.getCar().getMileage() + rental.getKm());

        rentalRepository.save(rental);
    }

    public List<Rental> findRunningRentals() {
        return rentalRepository.findRunningRentals();
    }

    public List<Rental> findAllRentals() {
        return rentalRepository.findAll();
    }

    public List<Rental> findByCar(Car car) {
        return rentalRepository.findByCar(car);
    }

    public Optional<Rental> existsAndCanFinish(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        Optional<Rental> opt = rentalRepository.findById(id);
        Rental rental;
        if (opt.isPresent() && canFinish((rental = opt.get()))) {
            return Optional.of(rental);
        } else {
            return Optional.empty();
        }
    }

    public boolean canFinish(Rental rental) {
        return rental.getReturnDate() == null && rental.getKm() == null && rental.getReturnStation() == null;
    }

    public boolean canCreate(Rental rental) {
        return carService.findByStation(rental.getRentalStation()).contains(rental.getCar());
    }

    public boolean cleanDates(Rental rental, FinishRentalBean finishRentalBean) {
        if (rental.getRentalDate().isAfter(finishRentalBean.getReturnDate())) {
            return false;
        } else {
            rental.setReturnDate(finishRentalBean.getReturnDate());
            return true;
        }
    }

    public void deleteById(Integer id) throws Exception{
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        rentalRepository.delete(rental);
    }

}
