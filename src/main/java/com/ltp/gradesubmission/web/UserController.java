package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }




}
