package com.example.inventorywebservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String farmerName;
    private String message;
    private LocalDateTime timestamp;

    public Notification() {}

    public Notification(String farmerName, String message) {
        this.farmerName = farmerName;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getFarmerName() { return farmerName; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}