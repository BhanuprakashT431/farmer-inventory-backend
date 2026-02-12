package com.example.inventorywebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired private ProductService productService; // Use the Service we built!
    @Autowired private ProductRepository productRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private NotificationRepository notificationRepository;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product add(@RequestBody Product p) {
        // Record the addition in the history
        transactionRepository.save(new Transaction("ADDED", p.getProductName(), p.getQuantity(), p.getPrice(), p.getFarmerName(), null));
        return productService.addProduct(p);
    }

    // Changed String id to Long id to match your new Product.java
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        p.setId(id); // Ensure the object has the correct ID before saving
        transactionRepository.save(new Transaction("UPDATED", p.getProductName(), p.getQuantity(), p.getPrice(), p.getFarmerName(), null));
        return productService.addProduct(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if(productRepository.existsById(id)) {
            Product p = productRepository.findById(id).get();
            transactionRepository.save(new Transaction("DELETED", p.getProductName(), p.getQuantity(), p.getPrice(), p.getFarmerName(), null));
            productRepository.deleteById(id);
        }
    }

    @PostMapping("/{id}/buy")
    public String buy(@PathVariable Long id, @RequestBody Map<String, Object> req) {
        int qty = Integer.parseInt(req.get("quantity").toString());
        String buyerName = (String) req.get("buyerName");

        // Use the 'recordSale' logic from our ProductService!
        String result = productService.recordSale(id, qty);

        if (result.startsWith("Sale successful")) {
            Product p = productRepository.findById(id).get();
            // Record the transaction for your graphs and history
            transactionRepository.save(new Transaction("SOLD", p.getProductName(), qty, p.getPrice(), p.getFarmerName(), buyerName));

            // Send a notification to the farmer
            String msg = "New Sale! " + buyerName + " bought " + qty + " units of " + p.getProductName();
            notificationRepository.save(new Notification(p.getFarmerName(), msg));
            return "Success";
        }

        return result; // Returns "Insufficient Stock" or "Product not found"
    }

    @GetMapping("/history")
    public List<Transaction> getHistory() { return transactionRepository.findAll(); }

    @GetMapping("/history/farmer/{name}")
    public List<Transaction> getFarmerHistory(@PathVariable String name) { return transactionRepository.findByFarmerName(name); }

    @GetMapping("/history/buyer/{name}")
    public List<Transaction> getBuyerHistory(@PathVariable String name) { return transactionRepository.findByBuyerName(name); }

    @GetMapping("/notifications/{farmerName}")
    public List<Notification> getNotifications(@PathVariable String farmerName) { return notificationRepository.findByFarmerName(farmerName); }

    @DeleteMapping("/notifications/{id}")
    public void deleteNotification(@PathVariable Long id) { notificationRepository.deleteById(id); }
    // URL: http://localhost:8080/products/revenue
    @GetMapping("/revenue")
    public Map<String, Double> getTotalRevenue() {
        double total = productService.calculateTotalRevenue();
        // Returning a Map makes it look nice in the browser as JSON
        return Map.of("totalRevenue", total);
    }
    // URL: http://localhost:8080/products/scan/{barcode}
    @GetMapping("/scan/{barcode}")
    public Product getByBarcode(@PathVariable String barcode) {
        return productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found for barcode: " + barcode));
    }
}