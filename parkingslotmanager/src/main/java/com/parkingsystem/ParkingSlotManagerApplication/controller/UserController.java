package com.parkingsystem.ParkingSlotManagerApplication.controller;

import com.parkingsystem.ParkingSlotManagerApplication.model.User;
import com.parkingsystem.ParkingSlotManagerApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // Add new user
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Update user
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
