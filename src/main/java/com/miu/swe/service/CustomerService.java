package com.miu.swe.service;


import com.miu.swe.model.Car;
import com.miu.swe.model.Customer;
import com.miu.swe.repository.CustomerRepository;
import com.miu.swe.repository.RentalRepository;
import com.miu.swe.util.MessagesBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {


    private MessagesBean messages;
    private CustomerRepository customerRepository;
    private RentalRepository rentalRepository;

    public CustomerService(MessagesBean messages, CustomerRepository customerRepository,RentalRepository rentalRepository) {
        this.messages = messages;
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer) {
        if (customerRepository.existsById(customer.getCustomerNumber())) {
            throw new EntityExistsException(messages.get("customerAlreadyExists"));
        }
        return customerRepository.save(customer);
    }

    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    public void deleteById(Integer id) throws Exception{
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.get("customerNotFound")));
        if (!canDelete(customer)) {
            throw new IllegalArgumentException(messages.get("customerDeleteError"));
        }
        customerRepository.delete(customer);
    }

    public boolean canDelete(Customer customer) {
        return rentalRepository.findByDriver(customer).isEmpty();
    }
}
