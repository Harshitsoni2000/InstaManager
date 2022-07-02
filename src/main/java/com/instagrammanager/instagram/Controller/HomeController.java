package com.instagrammanager.instagram.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Home Page
    @GetMapping("/")
    public String getHomePage() {
        return "HomePage";
    }
}
