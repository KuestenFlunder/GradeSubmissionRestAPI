package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.exceptions.EntityNotFoundException;
import com.ltp.gradesubmission.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User returnedUser= userRepository.save(user);
        System.out.println(returnedUser);
        return returnedUser;
    }

    @Override
    public Set<User> getUsers() {
        return (Set<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.
                findById(userId).
                orElseThrow(()-> new EntityNotFoundException(userId,User.class));
    }

    @Override
    public User updateUserById(Long userId, User user) {
        User existiongUser = userRepository.
                findById(userId).
                orElseThrow(()-> new EntityNotFoundException(userId,User.class));
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
                orElseThrow(()-> new EntityNotFoundException(username,User.class));
    }
}
