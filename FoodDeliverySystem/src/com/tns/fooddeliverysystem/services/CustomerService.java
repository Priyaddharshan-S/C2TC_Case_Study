package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.*;

import java.util.*;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) { customers.add(c); }
    public Customer findCustomerById(int id) {
        return customers.stream().filter(c -> c.getUserId() == id).findFirst().orElse(null);
    }
    public List<Customer> getCustomers() { return customers; }
}
