package com.miu.swe.service;

import com.miu.swe.model.Customer;
import com.miu.swe.repository.CustomerRepository;
import com.miu.swe.util.MessagesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class CustomerService {


    private MessagesBean messages;
    private CustomerRepository customerRepository;

    public CustomerService(MessagesBean messages, CustomerRepository customerRepository) {
        this.messages = messages;
        this.customerRepository = customerRepository;
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
}
