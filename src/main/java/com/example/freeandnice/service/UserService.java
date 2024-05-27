package com.example.freeandnice.service;

import com.example.freeandnice.model.User;
import com.example.freeandnice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setBalance(userDetails.getBalance());
            // Сохраняем обновленного пользователя
            return userRepository.save(user);
        } else {
            // Обработка случая, когда пользователь не найден
            return null;
        }
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}