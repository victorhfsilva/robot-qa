package com.example.robotqabackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.robotqabackend.infra.security.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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

    @Test
    @DisplayName("Controller should generate Token")
    public void generateTokenTest() throws Exception {
        String robotName = "Robot Name";
        String expectedToken = "token";
        when(tokenService.generateToken(eq(robotName))).thenReturn(expectedToken);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/robots/"+ robotName +"/token")).andReturn().getResponse();

        assertEquals(expectedToken, response.getContentAsString());
    }
}
