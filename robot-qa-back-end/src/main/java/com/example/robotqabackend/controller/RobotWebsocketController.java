package com.example.robotqabackend.controller;

import com.example.robotqabackend.domain.robot.Robot;
import com.example.robotqabackend.domain.robot.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class RobotWebsocketController {

    @Autowired
    private RobotService robotService;

    @MessageMapping("/robots/{robot}")
    @SendTo("/robots/{robot}")
    public String ask(@DestinationVariable("robot") String robotName, String question){
        Robot robot = robotService.findByName(robotName);
        Map<String, String> questionsAndAnswers = robot.getQuestionsAndAnswers();
        if (questionsAndAnswers.containsKey(question)) {
            return questionsAndAnswers.get(question);
        } else {
            return "Sorry, but I don't know how to answer that question.";
        }
    }

}
