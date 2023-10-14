package com.miu.swe.controller;

import com.miu.swe.model.Car;
import com.miu.swe.model.Customer;
import com.miu.swe.model.Rental;
import com.miu.swe.model.Station;
import com.miu.swe.service.CarService;
import com.miu.swe.service.CustomerService;
import com.miu.swe.service.RentalService;
import com.miu.swe.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private CarService carService;
    private CustomerService customerService;
    private RentalService rentalService;

    private StationService stationService;



    public AdminController(CarService carService, CustomerService customerService, RentalService rentalService, StationService stationService) {
        this.carService = carService;
        this.customerService = customerService;
        this.rentalService = rentalService;
        this.stationService = stationService;
    }

    @GetMapping("/create-car")
    public String showCreateCarForm(Model model, @ModelAttribute("car") Car car) {
        List<Station> stations = stationService.findAll();
        model.addAttribute("stations", stations);
        return "fragments/create-car";
    }

    @PostMapping("/create-car/refresh")
    public String refreshCreateCarForm(@ModelAttribute("rental") Car car, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("car", car);
        return "redirect:/admin/create-car";
    }

    @GetMapping("running-rentals")
    public List<Rental> findRunningRentals() {
        return rentalService.findRunningRentals();
    }

    @GetMapping("mileage-greater-than/{mileage}")
    public List<Car> findByMileageGreaterThan(@PathVariable Integer mileage) {
        return carService.findByMileageGreaterThan(mileage);
    }

    @PostMapping("create-car/process")
    public ResponseEntity<Car> createCar(@RequestBody @Valid Car car) {
        return ResponseEntity.created(URI.create("")).body(carService.create(car));
    }

    @PostMapping("create-customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        return ResponseEntity.created(URI.create("")).body(customerService.create(customer));
    }

    @DeleteMapping("delete-car/{registrationNr}")
    public void deleteCar(@PathVariable String registrationNr) {
        carService.deleteById(registrationNr);
    }

}
