package com.example.robotqabackend.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RobotUserRepository extends CrudRepository<RobotUser, Long> {

    public Optional<RobotUser> findByUsername(String username);
}
