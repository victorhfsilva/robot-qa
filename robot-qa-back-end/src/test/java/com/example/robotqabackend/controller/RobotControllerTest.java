package com.example.robotqabackend.controller;

import com.example.robotqabackend.domain.robot.Robot;
import com.example.robotqabackend.domain.robot.RobotDTO;
import com.example.robotqabackend.domain.robot.RobotService;
import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserService;
import com.example.robotqabackend.domain.user.Role;
import com.example.robotqabackend.infra.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.core.type.TypeReference;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RobotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private RobotController robotController;

    @Mock
    private RobotService robotService;

    @Mock
    private RobotUserService robotUserService;

    @Autowired
    private TokenService tokenService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Controller should get all robots.")
    public void findAllRobots() throws Exception {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        Robot robot1 = new Robot(
                "Robot Name 1",
                "Robot Description 1",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        List<Robot> robots = List.of(robot1);

        RobotUser user = new RobotUser(
                "victor",
                "password",
                robots,
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );


        when(robotUserService.findByUsername(any())).thenReturn(user);

        List<RobotDTO> expectedRobots = robots.stream().map(i -> i.toDTO()).toList();

        HttpHeaders headers = new HttpHeaders();
        String token = tokenService.generateToken(user.getUsername());
        headers.setBearerAuth(token);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/robots").headers(headers)).andReturn().getResponse();

        String actualRobotsJson = response.getContentAsString();
        List<RobotDTO> actualRobots = objectMapper.readValue(actualRobotsJson, new TypeReference<List<RobotDTO>>() {});

        assertIterableEquals(expectedRobots, actualRobots);
    }


}
