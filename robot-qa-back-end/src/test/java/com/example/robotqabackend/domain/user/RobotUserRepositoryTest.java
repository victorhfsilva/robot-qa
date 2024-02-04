package com.example.robotqabackend.domain.user;

import com.example.robotqabackend.domain.robot.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class RobotUserRepositoryTest {

    @Autowired
    private RobotUserRepository robotUserRepository;

    @BeforeEach
    void clearDatabase() {
        robotUserRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save user on database.")
    void saveUser() {
        RobotUser user = new RobotUser(
                "username",
                "password",
                List.of(),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        RobotUser savedUser = robotUserRepository.save(user);
        assertEquals(user, savedUser);
    }

    @Test
    @DisplayName("Should get saved user on test database.")
    void getUser() {
        RobotUser user = new RobotUser(
                "username",
                "password",
                List.of(),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        RobotUser savedUser = robotUserRepository.save(user);
        Long savedUserId = savedUser.getId();
        RobotUser expectedUser = robotUserRepository.findById(savedUserId).get();

        assertEquals(user, expectedUser);
    }

    @Test
    @DisplayName("Should get saved user by username on test database.")
    void getUserByUsername() {
        RobotUser user = new RobotUser(
                "username",
                "password",
                List.of(),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        RobotUser savedUser = robotUserRepository.save(user);
        String savedUsername = savedUser.getUsername();
        RobotUser expectedUser = robotUserRepository.findByUsername(savedUsername).get();

        assertEquals(user, expectedUser);
    }

}
