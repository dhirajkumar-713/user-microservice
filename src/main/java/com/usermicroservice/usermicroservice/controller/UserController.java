package com.usermicroservice.usermicroservice.controller;

import com.usermicroservice.usermicroservice.dto.ResponseDTO;
import com.usermicroservice.usermicroservice.entity.User;
import com.usermicroservice.usermicroservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userSaved = userService.saveUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        ResponseDTO userDTO = userService.getUser(userId);
        return ResponseEntity.ok(userDTO);
    }
}
