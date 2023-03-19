package com.jwt.jwtAuth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/omen")
    public String omen(){
        return "hmmmmm";
    }
}
