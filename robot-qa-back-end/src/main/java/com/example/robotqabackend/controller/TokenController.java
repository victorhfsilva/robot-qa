package com.example.robotqabackend.controller;

import com.example.robotqabackend.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/robots")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("/{robot}/token")
    public ResponseEntity<String> generateToken(@PathVariable("robot") String robot){
        String token = tokenService.generateToken(robot);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
