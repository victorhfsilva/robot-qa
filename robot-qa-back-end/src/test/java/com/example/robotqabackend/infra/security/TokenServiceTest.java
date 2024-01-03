package com.example.robotqabackend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.example.robotqabackend.domain.robot.Robot;
import com.example.robotqabackend.domain.robot.RobotService;
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
import java.util.Map;

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
    RobotService robotService;

    private String jwtSecret = "LetMeIn123!";

    private String issuer = "RobotQA";

    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    @Test
    @DisplayName("Should generate the right token")
    public void generateTokenTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        when(robotService.findByName(eq(robotName))).thenReturn(robot);

        String expectedToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(robotName)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        String actualToken = tokenService.generateToken(robotName);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    @DisplayName("Should validate the token correctly")
    public void validateTokenTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        when(robotService.findByName(eq(robotName))).thenReturn(robot);

        String token = tokenService.generateToken(robotName);

        assertTrue(tokenService.isTokenValid(token));
    }

    @Test
    @DisplayName("Should return false case the token subject is not valid.")
    public void validateWrongTokenSubjectTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        when(robotService.findByName(eq(robotName))).thenReturn(robot);

        String expectedToken = tokenService.generateToken(robotName);

        String insertedToken = JWT.create()
                .withIssuer(issuer)
                .withSubject("Wrong Robot Name")
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        assertFalse(tokenService.isTokenValid(insertedToken));
    }

    @Test
    @DisplayName("Should throw exception case the token issuer is not valid.")
    public void validateWrongTokenIssuerTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        when(robotService.findByName(eq(robotName))).thenReturn(robot);

        String expectedToken = tokenService.generateToken(robotName);

        String insertedToken = JWT.create()
                .withIssuer("Wrong Issuer")
                .withSubject(robotName)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        assertNotEquals(expectedToken, insertedToken);
        assertThrows(IncorrectClaimException.class, () -> tokenService.isTokenValid(insertedToken));
    }

    @Test
    @DisplayName("Should return false case the token key is not valid.")
    public void validateWrongTokenKeyTest(){
        Map<String, String> questionsAndAnswers = new HashMap<>();
        questionsAndAnswers.put("What is your name?", "My name is Robot");
        questionsAndAnswers.put("What is your purpose?", "To assist humans");

        String robotName = "Robot Name";

        Robot robot = new Robot(
                robotName,
                "Robot Description",
                questionsAndAnswers,
                "Robot Creator",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);

        when(robotService.findByName(eq(robotName))).thenReturn(robot);

        String expectedToken = tokenService.generateToken(robotName);

        Algorithm wrongAlgorithm = Algorithm.HMAC256("Wrong Secret");

        String insertedToken = JWT.create()
                .withIssuer(issuer)
                .withSubject(robotName)
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(wrongAlgorithm);

        assertNotEquals(expectedToken, insertedToken);
        assertFalse(tokenService.isTokenValid(insertedToken));
    }

}
