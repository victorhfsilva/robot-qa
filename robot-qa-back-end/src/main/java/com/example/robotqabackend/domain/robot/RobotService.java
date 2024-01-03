package com.example.robotqabackend.domain.robot;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RobotService implements IRobotService{

    @Autowired
    RobotRepository robotRepository;

    @Override
    public Robot save(Robot robot) {
        return robotRepository.save(robot);
    }

    @Override
    public Robot findByID(Long id) {
        Optional<Robot> robot = robotRepository.findById(id);
        if (robot.isEmpty()) {
            throw new EntityNotFoundException("Robot wasn't found on database.");
        } else {
            return robot.get();
        }
    }

    @Override
    public Robot findByName(String name) {
        Optional<Robot> robot = robotRepository.findByName(name);
        if (robot.isEmpty()) {
            throw new EntityNotFoundException("Robot wasn't found on database.");
        } else {
            return robot.get();
        }
    }

    @Override
    public List<Robot> findAll() {
        Iterable<Robot> robotsIterable = robotRepository.findAll();
        List<Robot> robots = StreamSupport
                .stream(robotsIterable.spliterator(), false)
                .collect(Collectors.toList());

        return robots;
    }


}
