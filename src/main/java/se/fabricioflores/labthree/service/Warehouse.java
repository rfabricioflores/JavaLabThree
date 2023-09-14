package se.fabricioflores.labthree.service;

import se.fabricioflores.labthree.entities.Category;
import se.fabricioflores.labthree.entities.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public final void addProduct(Product product) {
        if (product.name().isEmpty()) return;
        if (products.contains(product)) return;
        products.add(product);
    }

    public final void editProduct(int id, String name, Category category, int rating) {
        if (name.isEmpty()) return;
        if (category == null) return;

        var optionalProduct = products.stream().filter(p -> p.id() == id).findFirst();

        optionalProduct.ifPresent(product -> {
            Product editedProduct = new Product(
                    product.id(),
                    name,
                    category,
                    rating,
                    product.createdAt(),
                    LocalDateTime.now()
            );
            products.remove(product);
            products.add(editedProduct);
        });
    }

    public final List<Product> getAllProducts() {
        return products;
    }

    public final Optional<Product> getProductByID(int productId) {
        return products.stream().filter(p -> p.id() == productId).findFirst();
    }

    public final List<Product> getProductsByCategorySortedByName(Category category) {
        return products.stream()
                .filter(p -> p.category() == category)
                .sorted(Comparator.comparing(Product::name))
                .toList();
    }

    public final List<Product> getProductsCreatedAfterDate(LocalDateTime date) {
        return products.stream()
                .filter(p -> p.createdAt().isAfter(date))
                .toList();
    }

    public final List<Product> getProductsModifiedAfterCreation() {
        return products.stream()
                .filter(p -> !p.editedAt().equals(p.createdAt()))
                .toList();
    }
}