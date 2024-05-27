package com.example.freeandnice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.freeandnice.model.User;
import com.example.freeandnice.repository.UserRepository;
import com.example.freeandnice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }


    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1);

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(1, result.getId());
    }


    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1);

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setName("Updated Name"); // Установите необходимые данные для пользователя

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        User result = userService.updateUser(1, updatedUser);

        assertNotNull(result);
        assertEquals(1, result.getId());
        // Проверьте другие поля, если необходимо
    }}