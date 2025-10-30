package com.Ecommerce_Template.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {

    @GetMapping("/api/ping")
    public String ping() {
        return "pong";
    }
}
