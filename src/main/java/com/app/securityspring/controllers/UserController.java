package com.app.securityspring.controllers;

import com.app.securityspring.dto.UserReqDto;
import com.app.securityspring.dto.UserResDto;
import com.app.securityspring.entity.Student;
import com.app.securityspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final List<Student> students= new ArrayList<>();

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("/register")
    public UserResDto register(@RequestBody UserReqDto userReqDto){
        return userService.register(userReqDto);
    }

    @PostMapping("/login")
    public  String login(@RequestBody UserReqDto userReqDto){
        return userService.login(userReqDto);
    }
}
