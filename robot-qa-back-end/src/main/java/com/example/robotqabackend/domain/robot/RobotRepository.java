package com.example.robotqabackend.domain.robot;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RobotRepository extends CrudRepository<Robot, Long> {

    public Optional<Robot> findByName(String name);
}
