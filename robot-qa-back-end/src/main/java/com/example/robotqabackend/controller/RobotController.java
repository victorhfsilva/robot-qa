package com.example.robotqabackend.controller;

import com.auth0.jwt.JWT;
import com.example.robotqabackend.domain.robot.RobotDTO;
import com.example.robotqabackend.domain.robot.RobotService;
import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserService;
import com.example.robotqabackend.infra.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/robots")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @Autowired
    private RobotUserService robotUserService;

    @GetMapping()
    public ResponseEntity<List<RobotDTO>> getAllAuthorized(@RequestHeader("Authorization") String authorizationHeader) {
        String token = TokenUtils.extractToken(authorizationHeader);
        RobotUser subject = robotUserService.findByUsername(JWT.decode(token).getSubject());
        List<RobotDTO> content = subject.getRobots().stream().map(i -> i.toDTO()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

}
