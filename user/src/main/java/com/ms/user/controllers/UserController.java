package com.ms.user.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.UserRecordDTO;
import com.ms.user.models.User;
import com.ms.user.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO){
        var user = new User();
        BeanUtils.copyProperties(userRecordDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

}
