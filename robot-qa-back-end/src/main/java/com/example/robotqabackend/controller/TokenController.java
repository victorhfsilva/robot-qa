package com.example.robotqabackend.controller;

import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserDTO;
import com.example.robotqabackend.domain.user.RobotUserService;
import com.example.robotqabackend.infra.security.RobotUserDetailsService;
import com.example.robotqabackend.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/robots")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private RobotUserService robotUserService;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody RobotUserDTO user){
        RobotUser savedUser = robotUserService.findByUsername(user.getUsername());
        System.out.println(savedUser.getPassword() + " inserted:" + user.getPassword());
        if (savedUser.getPassword().equals(user.getPassword())) {
            String token = tokenService.generateToken(user.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

}
