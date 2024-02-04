package com.example.robotqabackend.domain.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RobotUserDTO {

    private String username;

    private String password;

    private String robot;

}
