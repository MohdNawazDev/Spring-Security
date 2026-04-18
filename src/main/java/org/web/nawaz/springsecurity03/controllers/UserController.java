package org.web.nawaz.springsecurity03.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/course")
    public String course()
    {
        return "You have enrolled in Devops Course";
    }


    @GetMapping("/profile")
    public String profile()
    {
        return "Your Profile";
    }




}
