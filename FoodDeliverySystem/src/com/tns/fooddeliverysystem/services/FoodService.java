package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.*;
import java.util.*;

public class FoodService {
    private List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(Restaurant r) { restaurants.add(r); }
    public Restaurant findRestaurantById(int id) {
        return restaurants.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }
    public List<Restaurant> getRestaurants() { return restaurants; }
}

