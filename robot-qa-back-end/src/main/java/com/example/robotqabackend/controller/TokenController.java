package com.example.robotqabackend.controller;

import com.example.robotqabackend.domain.user.RobotUserDTO;
import com.example.robotqabackend.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/robots")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody RobotUserDTO user){
        String token = tokenService.generateToken(user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
