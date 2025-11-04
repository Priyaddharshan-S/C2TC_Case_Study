package com.tns.fooddeliverysystem.application;

import com.tns.fooddeliverysystem.entities.*;
import com.tns.fooddeliverysystem.services.*;

import java.util.*;

public class FoodDeliverySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FoodService foodService = new FoodService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        List<DeliveryPerson> deliveryPersons = new ArrayList<>();
        int nextOrderId = 1;

        while (true) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            if (choice == 1) {
                // Admin Menu
                int opt;
                do {
                    System.out.println("Admin Menu");
                    System.out.println("1. Add Restaurant");
                    System.out.println("2. Add Food Item to Restaurant");
                    System.out.println("3. Remove Food Item from Restaurant");
                    System.out.println("4. View Restaurants and Menus");
                    System.out.println("5. View Orders");
                    System.out.println("6. Add Delivery Person");
                    System.out.println("7. Assign Delivery Person to Order");
                    System.out.println("8. Exit");
                    opt = sc.nextInt();
                    switch (opt) {
                        case 1:
                            System.out.print("Enter Restaurant ID ");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Restaurant Name ");
                            String name = sc.nextLine();
                            foodService.addRestaurant(new Restaurant(id, name));
                            System.out.println("Restaurant added successfully!");
                            break;
                        case 2:
                            System.out.print("Enter Restaurant ID ");
                            id = sc.nextInt();
                            Restaurant rest = foodService.findRestaurantById(id);
                            if (rest == null) { System.out.println("Restaurant not found!"); break; }
                            System.out.print("Enter Food Item ID ");
                            int fid = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Food Item Name ");
                            String fname = sc.nextLine();
                            System.out.print("Enter Food Item Price ");
                            double price = sc.nextDouble();
                            rest.addFoodItem(new FoodItem(fid, fname, price));
                            System.out.println("Food item added successfully!");
                            break;
                        case 3:
                            System.out.print("Enter Restaurant ID ");
                            id = sc.nextInt();
                            Restaurant resR = foodService.findRestaurantById(id);
                            if (resR == null) { System.out.println("Restaurant not found!"); break;}
                            System.out.print("Enter Food Item ID ");
                            int rmId = sc.nextInt();
                            resR.removeFoodItem(rmId);
                            System.out.println("Food item removed from restaurant!");
                            break;
                        case 4:
                            for (Restaurant r : foodService.getRestaurants()) {
                                System.out.println(r);
                            }
                            break;
                        case 5:
                            for (Order o : orderService.getOrders()) {
                                System.out.println(o);
                            }
                            break;
                        case 6:
                            System.out.print("Enter Delivery Person ID ");
                            int dpId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Delivery Person Name ");
                            String dpName = sc.nextLine();
                            System.out.print("Enter Contact No. ");
                            long dpContact = sc.nextLong();
                            deliveryPersons.add(new DeliveryPerson(dpId, dpName, dpContact));
                            System.out.println("Delivery person added successfully!");
                            break;
                        case 7:
                            System.out.print("Enter Order ID ");
                            int oId = sc.nextInt();
                            Order ord = orderService.findOrderById(oId);
                            if (ord == null) { System.out.println("Order not found!"); break;}
                            System.out.print("Enter Delivery Person ID ");
                            int dpId2 = sc.nextInt();
                            DeliveryPerson dPerson = deliveryPersons.stream().filter(d -> d.getDeliveryPersonId() == dpId2).findFirst().orElse(null);
                            if (dPerson == null) { System.out.println("Delivery person not found!"); break;}
                            ord.setDeliveryPerson(dPerson);
                            ord.setStatus("Assigned");
                            System.out.println("Delivery person assigned to order successfully!");
                            break;
                        case 8:
                            System.out.println("Exiting Admin Module...");
                    }
                } while (opt != 8);
            } else if (choice == 2) {
                // Customer Menu
                int opt;
                do {
                    System.out.println("Customer Menu");
                    System.out.println("1. Add Customer");
                    System.out.println("2. View Food Items");
                    System.out.println("3. Add Food to Cart");
                    System.out.println("4. View Cart");
                    System.out.println("5. Place Order");
                    System.out.println("6. View Orders");
                    System.out.println("7. Exit");
                    opt = sc.nextInt();
                    switch (opt) {
                        case 1:
                            System.out.print("Enter User ID ");
                            int userId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Username ");
                            String username = sc.nextLine();
                            System.out.print("Enter Contact No. ");
                            long contact = sc.nextLong();
                            customerService.addCustomer(new Customer(userId, username, contact));
                            System.out.println("Customer created successfully!");
                            break;
                        case 2:
                            for (Restaurant r : foodService.getRestaurants()) {
                                System.out.println(r);
                            }
                            break;
                        case 3:
                            System.out.print("Enter Customer ID ");
                            int custId = sc.nextInt();
                            Customer cust = customerService.findCustomerById(custId);
                            if (cust == null) { System.out.println("Customer not found!"); break; }
                            System.out.print("Enter Restaurant ID ");
                            int restId = sc.nextInt();
                            Restaurant rest = foodService.findRestaurantById(restId);
                            if (rest == null) { System.out.println("Restaurant not found!"); break;}
                            System.out.print("Enter Food Item ID ");
                            int foodId = sc.nextInt();
                            FoodItem item = rest.getMenu().stream().filter(f -> f.getId() == foodId).findFirst().orElse(null);
                            if (item == null) { System.out.println("Food item not found!"); break;}
                            System.out.print("Enter Quantity ");
                            int qty = sc.nextInt();
                            cust.getCart().addItem(item, qty);
                            System.out.println("Food item added to cart!");
                            break;
                        case 4:
                            System.out.print("Enter Customer ID ");
                            int custId2 = sc.nextInt();
                            Customer cust2 = customerService.findCustomerById(custId2);
                            if (cust2 == null) { System.out.println("Customer not found!"); break; }
                            System.out.println("Cart\n" + cust2.getCart());
                            break;
                        case 5:
                            System.out.print("Enter Customer ID ");
                            int custId3 = sc.nextInt();
                            Customer cust3 = customerService.findCustomerById(custId3);
                            if (cust3 == null) { System.out.println("Customer not found!"); break;}
                            sc.nextLine();
                            System.out.print("Enter Delivery Address ");
                            String address = sc.nextLine();
                            Order newOrder = new Order(nextOrderId++, cust3, address);
                            orderService.addOrder(newOrder);
                            System.out.println("Order placed successfully! Your order ID is " + newOrder.getOrderId());
                            cust3.getCart().getItems().clear();
                            break;
                        case 6:
                            for (Order o : orderService.getOrders()) {
                                System.out.println(o);
                            }
                            break;
                        case 7:
                            System.out.println("Exiting Customer Module...");
                    }
                } while (opt != 7);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }
}
