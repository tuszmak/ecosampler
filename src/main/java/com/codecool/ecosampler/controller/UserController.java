package com.codecool.ecosampler.controller;

import com.codecool.ecosampler.controller.dto.user.NewUser;
import com.codecool.ecosampler.controller.dto.user.UserDTO;
import com.codecool.ecosampler.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }

    @PostMapping("")
    public Long registerUser(@RequestBody NewUser newUser){
       return userService.registerUser(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserByID(id);
    }

}