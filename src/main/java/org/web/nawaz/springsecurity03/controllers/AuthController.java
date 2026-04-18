package org.web.nawaz.springsecurity03.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web.nawaz.springsecurity03.dto.RegisterRequest;
import org.web.nawaz.springsecurity03.entity.User;
import org.web.nawaz.springsecurity03.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register") //for user
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest request)
    {
        try {
            User user = userService.RegisterUser(request);
            Map<String, Object> response = new HashMap<>();

            response.put("success",true);
            response.put("message", "User Registered Successfully");
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("roles", user.getRoles().stream().map(role -> role.getName()).toList());

            return ResponseEntity.ok(response);
        } catch (Exception e)
        {
            Map<String, Object> response = new HashMap<>();

            response.put("success", false);
            response.put("message", "User not registered Successfully");
            return ResponseEntity.ok(response);

        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<Map<String, Object>> registerAdmin(@Valid @RequestBody RegisterRequest request)
    {
        try {
            User user = userService.RegisterAdmin(request);
            Map<String, Object> response = new HashMap<>();

            response.put("success",true);
            response.put("message", "Admin Registered Successfully");
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("roles", user.getRoles().stream().map(role -> role.getName()).toList());

            return ResponseEntity.ok(response);
        } catch (Exception e)
        {
            Map<String, Object> response = new HashMap<>();

            response.put("success", false);
            response.put("message", "User not registered Successfully");
            return ResponseEntity.ok(response);

        }
    }
}
