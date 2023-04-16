package com.example.blog_backend.Controller;

import com.example.blog_backend.Dto.UserRequestDto;
import com.example.blog_backend.Entity.User;
import com.example.blog_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto){
    return userService.createUser(userRequestDto);
    }
    @PostMapping("/login")
    public String validateUser(@RequestBody UserRequestDto userRequestDto){
        return userService.validateUser(userRequestDto);
    }
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestBody UserRequestDto userRequestDto){
        return userService.updatePassword(userRequestDto);
    }

}
