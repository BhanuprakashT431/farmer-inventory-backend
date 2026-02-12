package com.example.inventorywebservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This auto-generates numbers (1, 2, 3...)
    private Long id; // Changed from String productId to Long id

    private String productName;
    private int quantity;
    private double price;
    private LocalDate expiryDate;
    private String farmerName;
    private String farmerPhone;
    private String farmerEmail;
    private String barcode;


    // Required by JPA
    public Product() {}

    // Constructor (Note: we removed ID from here because the database creates it)
    public Product(String productName, int quantity, double price, LocalDate expiryDate, String farmerName, String farmerPhone, String farmerEmail) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.farmerName = farmerName;
        this.farmerPhone = farmerPhone;
        this.farmerEmail = farmerEmail;

    }

    // --- Updated Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getFarmerName() { return farmerName; }
    public void setFarmerName(String farmerName) { this.farmerName = farmerName; }

    public String getFarmerPhone() { return farmerPhone; }
    public void setFarmerPhone(String farmerPhone) { this.farmerPhone = farmerPhone; }

    public String getFarmerEmail() { return farmerEmail; }
    public void setFarmerEmail(String farmerEmail) { this.farmerEmail = farmerEmail; }
     // Add this field

    // Add getter and setter
    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
}