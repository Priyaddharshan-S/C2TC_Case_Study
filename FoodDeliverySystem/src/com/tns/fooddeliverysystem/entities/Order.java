package com.tns.fooddeliverysystem.entities;

import java.util.*;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items;
    private String status = "Pending";
    private DeliveryPerson deliveryPerson = null;
    private String deliveryAddress;

    public Order(int id, Customer customer, String address) {
        this.orderId = id;
        this.customer = customer;
        this.items = new HashMap<>(customer.getCart().getItems());
        this.deliveryAddress = address;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public void setDeliveryPerson(DeliveryPerson dp) { this.deliveryPerson = dp; }
    public void setStatus(String s) { this.status = s; }
    public String getStatus() { return status; }
    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order" +
                orderId + ", customer" + customer.getUsername() + ", items");
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            sb.append(e.getKey().toString()).append(e.getValue()).append(", ");
        }
        sb.append("status").append(status).append(", deliveryPerson")
                .append(deliveryPerson == null ? "Not Assigned" : deliveryPerson.getName());
        return sb.toString();
    }
}
