package com.example.robotqabackend.domain.robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class RobotRepositoryTest {

    @Autowired
    private RobotRepository robotRepository;

    @BeforeEach
    void clearDatabase() {
        robotRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save robot on test database.")
    void saveRobot() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        Robot robot = new Robot(
                "RobotName",
                "RobotDescription",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        Robot savedRobot = robotRepository.save(robot);
        assertEquals(robot, savedRobot);
    }

    @Test
    @DisplayName("Should get saved robot on test database.")
    void getRobot() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        Robot robot = new Robot(
                "RobotName",
                "RobotDescription",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        Robot savedRobot = robotRepository.save(robot);
        Long savedRobotId = savedRobot.getId();
        Robot expectedRobot = robotRepository.findById(savedRobotId).get();

        assertEquals(robot, expectedRobot);
    }

    @Test
    @DisplayName("Should return Empty Optional if entity is not saved on test database.")
    void getNonExistingRobot() {
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        Robot robot = new Robot(
                "RobotName",
                "RobotDescription",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        Robot savedRobot = robotRepository.save(robot);
        Long savedRobotId = savedRobot.getId();
        Optional<Robot> expectedRobot = robotRepository.findById(savedRobotId+1);

        assertTrue(expectedRobot.isEmpty());
    }

}
