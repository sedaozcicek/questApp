package com.ozcicek.questionapp.services;

import com.ozcicek.questionapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    User getUser(Long userId);

    User updateUser(Long userId, User updateUser);

    void deleteUser(Long userId);
}
