package com.example.robotqabackend.infra.security;

import com.example.robotqabackend.domain.user.RobotUser;
import com.example.robotqabackend.domain.user.RobotUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotUserDetailsService implements UserDetailsService {

    @Autowired
    private RobotUserService robotUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            RobotUser robotUser = robotUserService.findByUsername(username);
            String password = robotUser.getPassword();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(robotUser.getRole().name()));
            return new User(username, password, authorities);
        } catch(EntityNotFoundException ex) {
            throw new UsernameNotFoundException("Username not fount.");
        }
    }
}
