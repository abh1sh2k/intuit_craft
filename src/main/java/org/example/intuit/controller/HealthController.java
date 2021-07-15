package org.example.intuit.controller;

import org.example.intuit.constants.ResourceUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping(ResourceUrl.HEALTHCHECK)
    public String health() {
        return "OK";
    }
}
