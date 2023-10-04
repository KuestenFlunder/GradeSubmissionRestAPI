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
    public ResponseEntity<HttpStatus> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/id/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId).getUsername(), HttpStatus.FOUND);
    }

    @PutMapping(value = "/{userId}/update")
    public ResponseEntity<String> updateUserById(@PathVariable Long userId, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserById(userId, user).getUsername(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{userId}/delete")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<String> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.getUserByUsername(username).getUsername(),HttpStatus.OK);
    }



}
