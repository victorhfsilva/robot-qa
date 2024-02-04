package com.example.robotqabackend.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RobotUserServiceTest {

    @Autowired
    @InjectMocks
    private RobotUserService robotUserService;

    @Mock
    private RobotUserRepository robotUserRepository;

    @Test
    @DisplayName("Should get user by username")
    public void findByUsername(){
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

        when(robotUserRepository.findByUsername(eq("username"))).thenReturn(Optional.of(user));

        RobotUser actualUser = robotUserService.findByUsername("username");
        assertEquals(user, actualUser);
    }

}
