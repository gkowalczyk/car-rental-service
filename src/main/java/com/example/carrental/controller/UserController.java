package com.example.carrental.controller;

import com.example.carrental.domain.User;
import com.example.carrental.dto.UserDto;
import com.example.carrental.exception.UserNotFoundException;
import com.example.carrental.mapper.UserMapper;
import com.example.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.createUser(user);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    public ResponseEntity<UserDto> blockUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User userBlocked = userService.blockUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(userBlocked));

    }

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestParam("user") String username, @RequestParam("password") String key) {
       // String token = userService.getJWTToken(key);
       // userService.saveUserDataLog(username, token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "allusers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsersFromDb();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }
}
