package com.example.robotqabackend.domain.robot;

import com.example.robotqabackend.domain.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper=false)
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
                    @JoinColumn(name = "questions_id", referencedColumnName = "id")
            })
    @MapKeyColumn(name = "question")
    @Column(name = "answers", nullable = false)
    private Map<String, String> questionsAndAnswers;

    public Robot(String name, String description, Map<String, String> questionsAndAnswers,
                 String createdBy, String updatedBy, String deactivatedBy,
                 LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deactivatedAt) {
        super(createdBy, updatedBy, deactivatedBy, createdAt, updatedAt, deactivatedAt);
        this.name = name;
        this.description = description;
        this.questionsAndAnswers = questionsAndAnswers;
    }

    public RobotDTO toDTO() {
        List<String> questions = questionsAndAnswers.keySet().stream().toList();
        return new RobotDTO(super.getId(), name, description, questions);
    }
}
