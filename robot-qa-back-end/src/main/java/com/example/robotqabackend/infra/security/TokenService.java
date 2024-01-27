package com.example.robotqabackend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.robotqabackend.domain.robot.Robot;
import com.example.robotqabackend.domain.robot.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private String jwtSecret = "LetMeIn123!";

    private String issuer = "RobotQA";

    @Autowired
    private RobotService robotService;

    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    public String generateToken(String robotName) {
        Robot robot = robotService.findByName(robotName);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(robot.getName())
                .withExpiresAt(LocalDateTime.now().plusHours(1L).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);
    }

    public Boolean isTokenValid(String token) {
        try {
            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            Robot subject = robotService.findByName(JWT.decode(token).getSubject());
            return subject != null;
        } catch (JWTDecodeException e) {
            return false;
        } catch (SignatureVerificationException e) {
            return false;
        }
    }

}
