package com.eazybytes.accounts.controller.mapper;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(CustomerDTO customerDTO, Customer customer){
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(Customer customer, CustomerDTO customerDTO){
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
