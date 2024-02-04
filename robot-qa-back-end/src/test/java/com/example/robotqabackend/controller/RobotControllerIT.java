package com.example.robotqabackend.controller;

import com.example.robotqabackend.domain.robot.Robot;
import com.example.robotqabackend.domain.robot.RobotDTO;
import com.example.robotqabackend.domain.robot.RobotRepository;
import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserRepository;
import com.example.robotqabackend.domain.user.Role;
import com.example.robotqabackend.infra.security.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RobotControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private RobotRepository robotRepository;

    @Autowired
    private RobotUserRepository robotUserRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplateBuilder builder;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void clearDatabase() {
        robotRepository.deleteAll();
        robotUserRepository.deleteAll();
    }

    @Test
    @DisplayName("IT: Controller should get all robots from test database.")
    public void findAllRobots() throws JsonProcessingException {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        RobotUser user = new RobotUser(
                "username",
                "password",
                List.of(),
                Role.ADMIN,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        RobotUser savedUser = robotUserRepository.save(user);

        Robot robot1 = new Robot(
                "Robot Name 1",
                "Robot Description 1",
                questionsAndAnswers,
                List.of(savedUser),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        Robot robot2 = new Robot(
                "Robot Name 2",
                "Robot Description 2",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        Robot savedRobot1 = robotRepository.save(robot1);
        Robot savedRobot2 = robotRepository.save(robot2);

        List<Robot> robots = List.of(robot1);
        List<RobotDTO> expectedRobots = robots.stream().map(i -> i.toDTO()).toList();

        HttpHeaders headers = new HttpHeaders();
        String token = tokenService.generateToken(user.getUsername());
        headers.setBearerAuth(token);

        RestTemplate template = builder.rootUri("http://localhost:" + port).build();
        ResponseEntity<String> response = template.exchange(RequestEntity.get("/api/v1/robots").headers(headers).build(), String.class);

        List<RobotDTO> actualRobots = objectMapper.readValue(response.getBody(), new TypeReference<List<RobotDTO>>() {});

        assertIterableEquals(expectedRobots, actualRobots);
    }
}
