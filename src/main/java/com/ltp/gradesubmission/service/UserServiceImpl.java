package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;
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
        return null;
    }

    @Override
    public User updateUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
