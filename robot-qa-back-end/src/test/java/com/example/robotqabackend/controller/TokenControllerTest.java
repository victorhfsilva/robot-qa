package com.example.robotqabackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserDTO;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private TokenController tokenController;

    @Mock
    private TokenService tokenService;

    @Mock
    private RobotUserService robotUserService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Controller should generate Token")
    public void generateTokenTest() throws Exception {
        String expectedToken = "token";
        RobotUserDTO robotUserDTO = new RobotUserDTO("username", "password");

        RobotUser robotUser = new RobotUser(
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

        String robotUserDTOJson = objectMapper.writeValueAsString(robotUserDTO);

        when(robotUserService.findByUsername("username")).thenReturn(robotUser);
        when(tokenService.generateToken(eq(robotUserDTO.getUsername()))).thenReturn(expectedToken);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/robots/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(robotUserDTOJson))
                .andReturn().getResponse();

        assertEquals(expectedToken, response.getContentAsString());
    }
}
