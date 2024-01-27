package com.example.robotqabackend.domain.robot;

import lombok.*;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RobotDTO {
    private Long id;
    private String name;
    private String description;
}
