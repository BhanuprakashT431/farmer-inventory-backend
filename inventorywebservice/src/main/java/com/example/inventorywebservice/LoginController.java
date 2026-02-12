package com.example.inventorywebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // --- LOGIN LOGIC ---
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        Map<String, String> response = new HashMap<>();

        // Check if user exists in Database
        if (userRepository.existsById(username)) {
            User user = userRepository.findById(username).get();
            if (user.getPassword().equals(password)) {
                response.put("status", "success");
                response.put("role", user.getRole());
                return response;
            }
        }

        response.put("status", "fail");
        return response;
    }

    // --- SIGNUP LOGIC ---
    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        if (userRepository.existsById(user.getUsername())) {
            return false; // User already exists
        }
        userRepository.save(user); // Save to Database
        return true;
    }
}