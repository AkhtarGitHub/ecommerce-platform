package com.ecommerce;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import com.ecommerce.util.DBConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDAOTest {

    private ProductDAO productDAO;

    @BeforeAll
    public void setUpDatabase() {
        // Initialize database connection for testing
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Clean up table and create a fresh one for testing
            stmt.execute("DROP TABLE IF EXISTS products");
            stmt.execute("CREATE TABLE products (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "price DOUBLE PRECISION, " +
                    "quantity INTEGER, " +
                    "seller_id INTEGER" +
                    ")");
            System.out.println("Test database setup completed.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to set up database for tests: " + e.getMessage(), e);
        }
    }

    @BeforeEach
    public void setUp() {
        productDAO = new ProductDAO();
    }

    @Test
    @DisplayName("Test Adding a Product")
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setQuantity(10);
        product.setSellerId(1);

        assertDoesNotThrow(() -> productDAO.addProduct(product));
        System.out.println("Add Product test passed.");
    }

    @Test
    @DisplayName("Test Retrieving All Products")
    public void testGetAllProducts() {
        List<Product> products = assertDoesNotThrow(() -> productDAO.getAllProducts());
        assertNotNull(products, "The product list should not be null.");
        assertFalse(products.isEmpty(), "The product list should not be empty.");
        System.out.println("Get All Products test passed.");
    }

    @Test
    @DisplayName("Test Updating a Product")
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(1); // Assuming the product ID 1 exists
        product.setName("Updated Product");
        product.setPrice(150.0);
        product.setQuantity(20);
        product.setSellerId(1);

        assertDoesNotThrow(() -> productDAO.updateProduct(product));
        System.out.println("Update Product test passed.");
    }

    @Test
    @DisplayName("Test Deleting a Product")
    public void testDeleteProduct() {
        int productId = 1; // Assuming the product ID 1 exists
        assertDoesNotThrow(() -> productDAO.deleteProduct(productId));
        System.out.println("Delete Product test passed.");
    }

    @AfterAll
    public void tearDownDatabase() {
        // Clean up the database after tests
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS products");
            System.out.println("Test database cleaned up.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to clean up database after tests: " + e.getMessage(), e);
        }
    }
}
