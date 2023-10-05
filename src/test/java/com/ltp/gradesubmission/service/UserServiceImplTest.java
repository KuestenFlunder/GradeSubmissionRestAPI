package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.exceptions.EntityNotFoundException;
import com.ltp.gradesubmission.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setPassword("plainPassword");
        when(bCryptPasswordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User returnedUser = userService.saveUser(user);

        assertEquals("encodedPassword", returnedUser.getPassword());
        verify(bCryptPasswordEncoder, times(1)).encode("plainPassword");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User returnedUser = userService.getUserById(1L);

        assertEquals(user, returnedUser);
    }

    @Test
    void getUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(1L);
        });
    }

    @Test
    void updateUserById() {
        User existingUser = new User();
        User newUser = new User();
        newUser.setUsername("newUsername");
        newUser.setPassword("newPassword");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(newUser);

        User updatedUser = userService.updateUserById(1L, newUser);

        assertEquals("newUsername", updatedUser.getUsername());
        assertEquals("newPassword", updatedUser.getPassword());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void getUserByUsername() {
        User user = new User();
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        User returnedUser = userService.getUserByUsername("username");

        assertEquals(user, returnedUser);
    }

    @Test
    void getUserByUsernameNotFound() {
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserByUsername("username");
        });
    }
}
