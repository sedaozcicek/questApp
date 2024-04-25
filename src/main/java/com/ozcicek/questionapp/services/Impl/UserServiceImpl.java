package com.ozcicek.questionapp.services.Impl;

import com.ozcicek.questionapp.entities.User;
import com.ozcicek.questionapp.repository.UserRepository;
import com.ozcicek.questionapp.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(Long userId,User updateUser) {
        User user = userRepository.findById(userId).orElse(null);
        if (Objects.nonNull(user)){
            setUserName(updateUser, user);
            setPassword(updateUser, user);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        deleteUser(userId, user);
    }

    private void deleteUser(Long userId, Optional<User> user) {
        if (user.isPresent()){
            userRepository.deleteById(userId);
        }
    }


    private static void setPassword(User updateUser, User user) {
        if (Objects.nonNull(updateUser.getPassword())){
            user.setPassword(updateUser.getPassword());
        }
    }

    private static void setUserName(User updateUser, User user) {
        if (Objects.nonNull(updateUser.getUserName())){
            user.setUserName(updateUser.getUserName());
        }
    }


}
