package com.example.robotqabackend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.example.robotqabackend.domain.robot.Robot;
import com.example.robotqabackend.domain.robot.RobotService;
import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserService;
import com.example.robotqabackend.domain.user.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Autowired
    @InjectMocks
    TokenService tokenService;

    @Mock
    RobotUserService robotUserService;

    private String jwtSecret = "LetMeIn123!";

    private String issuer = "RobotQA";

    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    @Test
    @DisplayName("Should generate the right token")
    public void generateTokenTest(){
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

        when(robotUserService.findByUsername(eq("username"))).thenReturn(user);

        String expectedToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUsername())
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        String actualToken = tokenService.generateToken(user.getUsername());

        assertEquals(expectedToken, actualToken);
    }

    @Test
    @DisplayName("Should validate the token correctly")
    public void validateTokenTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        String username = "username";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        RobotUser user = new RobotUser(
                username,
                "password",
                List.of(robot),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        when(robotUserService.findByUsername(eq(username))).thenReturn(user);

        String token = tokenService.generateToken(username);

        assertTrue(tokenService.isTokenValid(token, robotName));
    }

    @Test
    @DisplayName("Should return false case the token subject is not valid.")
    public void validateWrongTokenSubjectTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        String username = "username";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        RobotUser user = new RobotUser(
                username,
                "password",
                List.of(robot),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        when(robotUserService.findByUsername(eq(username))).thenReturn(user);

        String expectedToken = tokenService.generateToken(username);

        String insertedToken = JWT.create()
                .withIssuer(issuer)
                .withSubject("Wrong Username")
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        assertFalse(tokenService.isTokenValid(insertedToken, robotName));
    }

    @Test
    @DisplayName("Should return false case the subject does not has robot access.")
    public void validateWrongRobotSubjectTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        String username = "username";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        RobotUser user = new RobotUser(
                username,
                "password",
                List.of(robot),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        when(robotUserService.findByUsername(eq(username))).thenReturn(user);

        String token = tokenService.generateToken(username);

        assertFalse(tokenService.isTokenValid(token, "Wrong Robot Name"));
    }

    @Test
    @DisplayName("Should throw exception case the token issuer is not valid.")
    public void validateWrongTokenIssuerTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        String username = "username";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        RobotUser user = new RobotUser(
                username,
                "password",
                List.of(robot),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        when(robotUserService.findByUsername(eq(username))).thenReturn(user);

        String expectedToken = tokenService.generateToken(username);

        String insertedToken = JWT.create()
                .withIssuer("Wrong Issuer")
                .withSubject(robotName)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        assertNotEquals(expectedToken, insertedToken);
        assertThrows(IncorrectClaimException.class, () -> tokenService.isTokenValid(insertedToken, robotName));
    }

    @Test
    @DisplayName("Should return false case the token key is not valid.")
    public void validateWrongTokenKeyTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        String username = "username";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                List.of(),
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        RobotUser user = new RobotUser(
                username,
                "password",
                List.of(robot),
                Role.USER,
                "User Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null
        );

        when(robotUserService.findByUsername(eq(username))).thenReturn(user);

        String expectedToken = tokenService.generateToken(username);

        Algorithm wrongAlgorithm = Algorithm.HMAC256("Wrong Secret");

        String insertedToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(robotName)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(wrongAlgorithm);

        assertNotEquals(expectedToken, insertedToken);
        assertFalse(tokenService.isTokenValid(insertedToken, robotName));
    }

}
