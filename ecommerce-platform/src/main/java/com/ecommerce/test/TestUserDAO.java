package com.ecommerce.test;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

public class TestUserDAO {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        System.out.println("=== Testing UserDAO ===");

        // Test 1: Register a new user
        System.out.println("\n1. Registering a new user...");
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setEmail("john.doe@example.com");
        user.setRole("USER");

        boolean isInserted = userDAO.insertUser(user);
        if (isInserted) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Failed to register user.");
        }

        // Test 2: Find user by username
        System.out.println("\n2. Finding user by username...");
        String usernameToFind = "john_doe";
        User foundUser = userDAO.findByUsername(usernameToFind);
        if (foundUser != null) {
            System.out.println("User found: ");
            System.out.println("Username: " + foundUser.getUsername());
            System.out.println("Email: " + foundUser.getEmail());
            System.out.println("Role: " + foundUser.getRole());
        } else {
            System.out.println("No user found with username: " + usernameToFind);
        }

        // Test 3: Delete user by username
        System.out.println("\n3. Deleting user...");
        String usernameToDelete = "john_doe";
        boolean isDeleted = userDAO.deleteUser(usernameToDelete);
        if (isDeleted) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }

        System.out.println("\n=== UserDAO Testing Complete ===");
    }
}
