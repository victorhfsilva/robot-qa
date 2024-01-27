package com.example.robotqabackend.domain.robot;

import java.util.List;

public interface IRobotService {
    public Robot save(Robot robot);
    public Robot findByID(Long id);
    public Robot findByName(String name);
    public List<Robot> findAll();
}
