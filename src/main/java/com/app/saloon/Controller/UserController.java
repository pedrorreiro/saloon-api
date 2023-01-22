package com.app.saloon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.saloon.Dtos.UserDto;
import com.app.saloon.InputDto.Login;
import com.app.saloon.Model.User;
import com.app.saloon.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> listUsers() {
        return userService.getAll();
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody Login login) {
        return userService.userExistsByEmail(login.getEmail());
    }

    @GetMapping("/email/{email}")
    public UserDto getByEmail(@PathVariable String email) {
        return userService.userExistsByEmail(email);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.userExistsById(id);
    }

    @PostMapping
    public User save(@RequestBody UserDto user) {
        return userService.save(user);
    }
}
