package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;

public class ProductDAO {

    /**
     * Adds a new product to the database.
     *
     * @param product The product object containing details.
     */
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());

            stmt.executeUpdate();
            System.out.println("Product added successfully!");

        } catch (SQLException e) {
            throw new RuntimeException("Error adding product: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all products from the database.
     *
     * @return A list of products.
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSellerId(rs.getInt("seller_id"));
                products.add(product);
            }
            System.out.println("All products retrieved successfully!");

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving products: " + e.getMessage(), e);
        }
        return products;
    }

    /**
     * Updates the details of an existing product.
     *
     * @param product The product object containing updated details.
     */
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ?, seller_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());
            stmt.setInt(5, product.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("No product found with the given ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating product: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a product from the database.
     *
     * @param productId The ID of the product to delete.
     */
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("No product found with the given ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage(), e);
        }
    }

    // New method to get product by ID
    public Product getProductById(int productId) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSellerId(rs.getInt("seller_id"));
                return product;
            }

        } catch (Exception e) {
            System.err.println("Error retrieving product by ID: " + e.getMessage());
        }
        return null; // Return null if product is not found or an error occurs
    }
}
