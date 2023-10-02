package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping(value = "/users")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Set<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.FOUND);
    }

    @PutMapping(value = "user/{id}/update")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserById(userId, user), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "user/{userId}/delete")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
    }



}
