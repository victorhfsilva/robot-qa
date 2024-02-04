package com.example.robotqabackend.domain.user;

import com.example.robotqabackend.domain.BaseEntityAudit;
import com.example.robotqabackend.domain.robot.Robot;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper=false, exclude = {"robots"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class RobotUser extends BaseEntityAudit {

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Robot> robots;

    @Column(nullable = false)
    private Role role;

    public RobotUser(String username, String password, List<Robot> robots, Role role, String createdBy, String updatedBy, String deactivatedBy, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deactivatedAt) {
        super(createdBy, updatedBy, deactivatedBy, createdAt, updatedAt, deactivatedAt);
        this.username = username;
        this.password = password;
        this.robots = robots;
        this.role = role;
    }
}
