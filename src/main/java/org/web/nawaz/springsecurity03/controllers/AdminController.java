package org.web.nawaz.springsecurity03.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String Dashboard()
    {
        return "Welcome! to Admin Dashboard";
    }


    //already protected via url in config (/api/admin/**/)
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')") //EXTRA PROTECTION
    @Secured("Role_Admin")
    public List<String> getAllUsers()
    {
        List<String> users = new ArrayList<>();
        users.add(new String("Mohd"));
        users.add(new String("Nawaz"));
        users.add(new String("Qureshi"));
        users.add(new String("Zaifee"));

        return users;
    }


}
