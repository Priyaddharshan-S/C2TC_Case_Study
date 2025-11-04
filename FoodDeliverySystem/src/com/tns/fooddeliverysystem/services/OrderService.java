package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.*;
import java.util.*;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order o) { orders.add(o); }
    public Order findOrderById(int id) {
        return orders.stream().filter(o -> o.getOrderId() == id).findFirst().orElse(null);
    }
    public List<Order> getOrders() { return orders; }
}
