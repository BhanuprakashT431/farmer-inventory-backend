package com.example.inventorywebservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // SOLD, ADDED
    private String productName;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;
    private String farmerName; // Crucial for Farmer History
    private String buyerName;  // Crucial for Buyer History

    public Transaction() {}

    public Transaction(String type, String productName, int quantity, double price, String farmerName, String buyerName) {
        this.type = type;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.farmerName = farmerName;
        this.buyerName = buyerName;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getType() { return type; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getFarmerName() { return farmerName; }
    public String getBuyerName() { return buyerName; }
}