package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.exceptions.UserNotFoundException;
import com.ltp.gradesubmission.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Set<User> getUsers() {
        return (Set<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.
                findByUserId(userId).
                orElseThrow(()-> new UserNotFoundException(userId));
    }

    @Override
    public User updateUserById(Long userId, User user) {
        User existiongUser = userRepository.
                findByUserId(userId).
                orElseThrow(()-> new UserNotFoundException(userId));
        existiongUser.setUsername(user.getUsername());
        existiongUser.setPassword(user.getPassword());
        return userRepository.save(existiongUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserByUsername(String username){
        return userRepository.
                findByUsername(username).
                orElseThrow(()-> new UserNotFoundException(username));
    }
}
