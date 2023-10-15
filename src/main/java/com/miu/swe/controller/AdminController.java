package com.miu.swe.controller;

import com.miu.swe.bean.FinishRentalBean;
import com.miu.swe.model.Car;
import com.miu.swe.model.Customer;
import com.miu.swe.model.Rental;
import com.miu.swe.model.Station;
import com.miu.swe.service.CarService;
import com.miu.swe.service.CustomerService;
import com.miu.swe.service.RentalService;
import com.miu.swe.service.StationService;
import com.miu.swe.util.MessagesBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    private MessagesBean messages;
    private CarService carService;
    private CustomerService customerService;
    private RentalService rentalService;

    private StationService stationService;



    public AdminController(MessagesBean messages, CarService carService, CustomerService customerService, RentalService rentalService, StationService stationService) {
        this.messages = messages;
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


    @PostMapping("/create-car/process")
    public ModelAndView processCreateCarForm(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {

        ModelAndView createForm = new ModelAndView("fragments/create-car");
        List<Station> stations = stationService.findAll();
        createForm.addObject("stations", stations);

        if (bindingResult.hasErrors()) {
            return createForm;
        }

        carService.create(car);
        return new ModelAndView("redirect:/admin/all-cars")
                .addObject("success", messages.get("createCarSuccess"));
    }

    @GetMapping("/all-cars")
    public String showAllCarsForm(Model model, @ModelAttribute("error") String error, @ModelAttribute("success") String success) {
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        return "fragments/all-cars";
    }

    @GetMapping("/create-customer")
    public String showCreateCustomerForm(Model model, @ModelAttribute("customer") Customer customer) {
        return "fragments/create-customer";
    }


    @PostMapping("/create-customer/process")
    public ModelAndView processCreateCustomerForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        ModelAndView createForm = new ModelAndView("fragments/create-customer");

        if (bindingResult.hasErrors()) {
            return createForm;
        }

        customerService.create(customer);
        return new ModelAndView("redirect:/admin/all-customers")
                .addObject("success", messages.get("createCustomerSuccess"));
    }

    @GetMapping("/all-customers")
    public String showAllCustomerForm(Model model, @ModelAttribute("error") String error, @ModelAttribute("success") String success) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        return "fragments/all-customers";
    }

    @GetMapping("/delete-car/{id}")
    public ModelAndView showDeleteCarForm(@PathVariable("id") String id) {

        try {
            carService.deleteById(id);
        }catch (EntityNotFoundException exception){
            return new ModelAndView("redirect:/admin/all-cars")
                    .addObject("error", messages.get("deleteCarFailed"));
        }

        return new ModelAndView("redirect:/admin/all-cars")
                .addObject("success", messages.get("deleteCarSuccess"));
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteCustomerForm(@PathVariable("id") Integer id) {

        try {
            customerService.deleteById(id);
        }catch (EntityNotFoundException exception){
            return new ModelAndView("redirect:/admin/all-customers")
                    .addObject("error", messages.get("deleteCustomerFailed"));
        }

        return new ModelAndView("redirect:/admin/all-customers")
                .addObject("success", messages.get("deleteCustomerSuccess"));
    }


}
