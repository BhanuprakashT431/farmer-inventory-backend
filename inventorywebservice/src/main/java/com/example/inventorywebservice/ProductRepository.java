package com.example.inventorywebservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // This allows you to find a product by its barcode string
    Optional<Product> findByBarcode(String barcode);
}