package com.example.robotqabackend.controller;

import com.example.robotqabackend.domain.robot.RobotDTO;
import com.example.robotqabackend.domain.robot.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/robots")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @GetMapping()
    public ResponseEntity<List<RobotDTO>> getAll() {
        List<RobotDTO> content = robotService.findAll().stream().map(i -> i.toDTO()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }

}
