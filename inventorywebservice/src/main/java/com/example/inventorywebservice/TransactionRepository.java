package com.example.inventorywebservice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // This allows us to filter the history to see only sales
    List<Transaction> findByType(String type);

    List<Transaction> findByFarmerName(String name);
    List<Transaction> findByBuyerName(String name);
}