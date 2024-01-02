package com.example.robotqabackend.domain.robot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RobotServiceTest {

    @Autowired
    @InjectMocks
    private RobotService robotService;

    @Mock
    private RobotRepository robotRepository;

    @Test
    @DisplayName("Should get all robots.")
    public void findAllRobots() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        Robot robot1 = new Robot(
                "Robot Name 1",
                "Robot Description 1",
                questionsAndAnswers,
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
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        List<Robot> expectedRobots = List.of(robot1, robot2);
        Iterable<Robot> robotsIterable = expectedRobots;

        when(robotRepository.findAll()).thenReturn(robotsIterable);

        List<Robot> actualRobots = robotService.findAll();

        assertIterableEquals(expectedRobots, actualRobots);
    }


}
