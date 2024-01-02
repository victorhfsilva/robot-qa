package com.example.robotqabackend.domain.robot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Data
public class RobotDTO {
    private Long id;
    private String name;
    private String description;
}
