package com.ecommerce;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ProductService productService = new ProductService();

        // Use try-with-resources for Scanner to prevent resource leak
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                System.out.println("\n--- Product Management Menu ---");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        // Add Product
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();

                        System.out.print("Enter product quantity: ");
                        int quantity = scanner.nextInt();

                        System.out.print("Enter category ID: ");
                        int categoryId = scanner.nextInt();

                        Product product = new Product();
                        product.setName(name);
                        product.setPrice(price);
                        product.setQuantity(quantity);
                        product.setCategoryId(categoryId);

                        boolean isAdded = productService.addProduct(product);
                        if (isAdded) {
                            System.out.println("Product added successfully!");
                        } else {
                            System.out.println("Failed to add product.");
                        }
                        break;

                    case 2:
                        // View All Products
                        List<Product> products = productService.getAllProducts();
                        if (products.isEmpty()) {
                            System.out.println("No products available.");
                        } else {
                            System.out.println("\n--- Product List ---");
                            for (Product p : products) {
                                System.out.println("ID: " + p.getId() +
                                        ", Name: " + p.getName() +
                                        ", Price: " + p.getPrice() +
                                        ", Quantity: " + p.getQuantity() +
                                        ", Category ID: " + p.getCategoryId());
                            }
                        }
                        break;

                    case 3:
                        // Exit
                        System.out.println("Exiting... Thank you!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
