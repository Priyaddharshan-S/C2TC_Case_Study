package com.tns.fooddeliverysystem.entities;

import java.util.*;

public class Cart {
    private Map<FoodItem, Integer> items = new LinkedHashMap<>();

    public void addItem(FoodItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }

    public double getTotalCost() {
        return items.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            sb.append("Food Item ").append(entry.getKey().getName())
              .append(", Quantity ").append(entry.getValue())
              .append(", Cost Rs. ").append(entry.getKey().getPrice() * entry.getValue()).append("\n");
        }
        sb.append("Total Cost Rs. ").append(getTotalCost());
        return sb.toString();
    }
}
