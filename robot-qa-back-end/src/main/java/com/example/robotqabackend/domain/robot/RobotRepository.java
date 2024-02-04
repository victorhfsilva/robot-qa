package com.example.robotqabackend.domain.robot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RobotRepository extends CrudRepository<Robot, Long> {

    public Optional<Robot> findByName(String name);
}
