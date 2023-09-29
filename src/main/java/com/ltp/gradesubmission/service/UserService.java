package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.User;

import java.util.Set;

public interface UserService {

    User saveUser(User user);
    Set<User> getUsers();
    User getUserById(Long userId);
    User getUserByUsername( String username);
    User updateUserById(Long userId,User user);
    void deleteUser(Long userId);
}
