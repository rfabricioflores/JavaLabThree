package se.fabricioflores.labthree.service;

import se.fabricioflores.labthree.entities.Category;
import se.fabricioflores.labthree.entities.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.*;

public class WarehouseTest {
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
    }

    //    Add Product Tests
    @Test
    void testAddProduct() {
        var product = new Product(1, "Product 1", Category.TECH, 8);
        warehouse.addProduct(product);

        var allProducts = warehouse.getAllProducts();

        assertThat(allProducts).hasSize(1);
        assertThat(allProducts).containsExactly(product);
    }

    @Test
    void testAddProductWithEmptyName() {
        var product = new Product(1, "", Category.SPORT, 2);
        warehouse.addProduct(product);

        var allProducts = warehouse.getAllProducts();

        assertThat(allProducts).hasSize(0);
    }

    @Test
    void testAddTwoProductsWithSameId() {
        var product1 = new Product(1, "Ring", Category.JEWELRY, 2);
        var product2 = new Product(1, "Necklace", Category.SPORT, 5);

        warehouse.addProduct(product1);
        warehouse.addProduct(product2);

        var allProducts = warehouse.getAllProducts();

        assertThat(allProducts).hasSize(1);
        assertThat(allProducts).containsExactly(product1);
    }
}