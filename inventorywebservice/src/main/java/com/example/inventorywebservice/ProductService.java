package com.example.inventorywebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service // This tells Spring that this is the "Brain" (Logic Layer)
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // --- Add a Product ---
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // --- Get All Products ---
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // --- Low Stock Alert Logic ---
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findAll().stream()
                .filter(p -> p.getQuantity() <= threshold)
                .collect(Collectors.toList());
    }

    // --- Record a Sale ---
    public String recordSale(Long productId, int quantityToSell) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return "Product not found!";

        if (product.getQuantity() >= quantityToSell) {
            product.setQuantity(product.getQuantity() - quantityToSell);
            productRepository.save(product); // Saves the change to the database!
            return "Sale successful!";
        } else {
            return "Not enough stock!";
        }
    }
    @Autowired
    private TransactionRepository transactionRepository; // Add this at the top with other @Autowired

    public double calculateTotalRevenue() {
        List<Transaction> sales = transactionRepository.findByType("SOLD");

        // We use a stream to sum up: (quantity * price) for every sale
        return sales.stream()
                .mapToDouble(t -> t.getQuantity() * t.getPrice())
                .sum();
    }
}