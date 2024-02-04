package com.example.robotqabackend.domain.robot;

import com.example.robotqabackend.domain.BaseEntityAudit;
import com.example.robotqabackend.domain.user.RobotUser;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper=false, exclude = {"users"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "robots")
public class Robot extends BaseEntityAudit {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "questions_answers",
            joinColumns = {
                    @JoinColumn(name = "robot_id", referencedColumnName = "id")
            })
    @MapKeyColumn(name = "question")
    @Column(name = "answer", nullable = false)
    private Map<String, String> questionsAndAnswers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "robots_users",
            joinColumns = @JoinColumn(name="robot_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<RobotUser> users;

    public Robot(String name, String description, Map<String, String> questionsAndAnswers, List<RobotUser> users,
                 String createdBy, String updatedBy, String deactivatedBy,
                 LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deactivatedAt) {
        super(createdBy, updatedBy, deactivatedBy, createdAt, updatedAt, deactivatedAt);
        this.name = name;
        this.description = description;
        this.questionsAndAnswers = questionsAndAnswers;
        this.users = users;
    }

    public RobotDTO toDTO() {
        List<String> questions = questionsAndAnswers.keySet().stream().toList();
        return new RobotDTO(super.getId(), name, description, questions);
    }
}
