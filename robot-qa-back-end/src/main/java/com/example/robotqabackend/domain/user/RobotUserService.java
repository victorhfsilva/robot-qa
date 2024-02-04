package com.example.robotqabackend.domain.user;

import com.example.robotqabackend.domain.robot.Robot;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RobotUserService implements IRobotUserService{

    @Autowired
    RobotUserRepository robotUserRepository;

    @Override
    public RobotUser findByUsername(String username) {
        Optional<RobotUser> user = robotUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User wasn't found on database.");
        } else {
            return user.get();
        }
    }

}
