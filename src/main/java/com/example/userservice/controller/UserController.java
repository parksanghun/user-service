package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    private final Greeting greeting;
    private final UserService userService;
    private final Environment env;
    private final UserMapper mapper;

    @GetMapping("/health_check")
    public String status() {
        return String.format("This message is from User Service %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        UserDto userDto = mapper.toUserDto(user);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.toResponseUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
