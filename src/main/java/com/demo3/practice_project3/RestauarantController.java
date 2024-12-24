package com.demo3.practice_project3;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @RestController
    @RequestMapping("/api/restaurants")
    public class RestauarantController {

        // Temporary in-memory data for restaurants
        private List<Map<String, Object>> restaurants = new ArrayList<>();

        // Constructor to add sample data
        public RestauarantController() {
            Map<String, Object> restaurant1 = new HashMap<>();
            restaurant1.put("id", 1);
            restaurant1.put("name", "Tasty Treats");
            restaurant1.put("location", "Downtown");
            restaurant1.put("cuisine", "Italian");

            Map<String, Object> restaurant2 = new HashMap<>();
            restaurant2.put("id", 2);
            restaurant2.put("name", "Spicy Delights");
            restaurant2.put("location", "Uptown");
            restaurant2.put("cuisine", "Indian");

            restaurants.add(restaurant1);
            restaurants.add(restaurant2);
        }

        // Get all restaurants
        @GetMapping
        public List<Map<String, Object>> getAllRestaurants() {
            return restaurants;
        }

        // Get a restaurant by ID
        @GetMapping("/{id}")
        public Map<String, Object> getRestaurantById(@PathVariable int id) {
            return restaurants.stream()
                    .filter(restaurant -> (int) restaurant.get("id") == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Restaurant not found!"));
        }

        // Add a new restaurant
        @PostMapping
        public Map<String, Object> addRestaurant(@RequestBody Map<String, Object> restaurant) {
            restaurant.put("id", restaurants.size() + 1);
            restaurants.add(restaurant);
            return restaurant;
        }

        // Update an existing restaurant
        @PutMapping("/{id}")
        public Map<String, Object> updateRestaurant(@PathVariable int id, @RequestBody Map<String, Object> updatedRestaurant) {
            Map<String, Object> restaurant = getRestaurantById(id);
            restaurant.putAll(updatedRestaurant);
            return restaurant;
        }

        // Delete a restaurant
        @DeleteMapping("/{id}")
        public String deleteRestaurant(@PathVariable int id) {
            restaurants.removeIf(restaurant -> (int) restaurant.get("id") == id);
            return "Restaurant with ID " + id + " has been deleted.";
        }
    }

