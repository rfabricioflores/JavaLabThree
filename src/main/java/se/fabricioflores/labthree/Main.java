package se.fabricioflores.labthree;

import se.fabricioflores.labthree.entities.Category;
import se.fabricioflores.labthree.entities.Product;
import se.fabricioflores.labthree.service.Warehouse;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(new Product(1, "MAC", Category.TECH, 2));
        warehouse.addProduct(new Product(2, "BALL", Category.SPORT, 5));
        warehouse.addProduct(new Product(3, "SWITCH", Category.TECH, 3));
        warehouse.addProduct(new Product(4, "RING", Category.JEWELRY, 5));
        warehouse.addProduct(new Product(5, "CABLE", Category.TECH, 1));

        System.out.println("👉 Products sorted by category \"TECH\" and in alphabetical order");
        warehouse.getProductsByCategorySortedByName(Category.TECH).forEach(System.out::println);
        System.out.println();

        System.out.println("👉 Get product by id");
        System.out.println(warehouse.getProductByID(1).orElseThrow());
        System.out.println(warehouse.getProductByID(2).orElseThrow());
        System.out.println();

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("👉 Edit products with ids 2 and 5");
                        warehouse.editProduct(2, "Basketball", Category.SPORT, 4);
                        warehouse.editProduct(5, "Necklace", Category.JEWELRY, 5);
                        System.out.println(warehouse.getProductByID(2).orElseThrow());
                        System.out.println(warehouse.getProductByID(5).orElseThrow());
                        System.out.println();

                        System.out.println("👉 Get products modified after creation");
                        warehouse.getProductsModifiedAfterCreation().forEach(p -> System.out.println(p.name()));
                    }
                },
                1000
        );
    }
}