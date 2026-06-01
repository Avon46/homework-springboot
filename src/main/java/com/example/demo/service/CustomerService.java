package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateCustomerRequest;
import com.example.demo.entity.Customer;
import com.example.demo.enums.CustomerStatus;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.InvalidCustomerException;
import com.example.demo.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerMapper.findAll();
    }

    public Customer getByIdCustomer(int id) {
        Customer customer = customerMapper.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("找不到客戶id = " + id);
        }
        return customer;
    }

    public Customer createCustomer(CreateCustomerRequest ccr) {
        if (ccr.getName() == null || ccr.getName().isBlank()) {
            throw new InvalidCustomerException("客戶姓名不能為空");
        }

        if (ccr.getIdentityNumber() == null || ccr.getIdentityNumber().isBlank()) {
            throw new InvalidCustomerException("身分證字號不能為空");
        }
        Customer customer = new Customer(
                null, ccr.getName(), ccr.getPhone(), ccr.getEmail(), ccr.getIdentityNumber(),
                ccr.getBirthday(), CustomerStatus.ACTIVE);
        customerMapper.insert(customer);
        return customer;
    }

    public Customer updateCustomer(Integer id, CreateCustomerRequest ccr) {
        Customer customer = customerMapper.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("找不到客戶，id = " + id);
        }

        if (ccr.getName() == null || ccr.getName().isBlank()) {
            throw new InvalidCustomerException("客戶姓名不能為空");
        }
        customer.setName(ccr.getName());
        customer.setPhone(ccr.getPhone());
        customer.setEmail(ccr.getEmail());
        customer.setBirthday(ccr.getBirthday());
        customerMapper.update(customer);
        return customer;
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerMapper.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("找不到客戶，id = " + id);
        }
        customerMapper.deleteById(id);
    }

}
