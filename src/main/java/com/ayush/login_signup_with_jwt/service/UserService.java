package com.ayush.login_signup_with_jwt.service;
import com.ayush.login_signup_with_jwt.repository.UserRepository;
import com.ayush.login_signup_with_jwt.model.User;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}