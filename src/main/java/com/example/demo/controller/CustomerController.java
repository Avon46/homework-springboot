package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateCustomerRequest;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getByIdCustomer(@PathVariable("id") Integer id) {
        Customer customer = customerService.getByIdCustomer(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest ccr) {
        Customer customer = customerService.createCustomer(ccr);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@PathVariable("id") Integer id, @RequestBody CreateCustomerRequest ccr) {
        return customerService.updateCustomer(id, ccr);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
