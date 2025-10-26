package com.parkingsystem.ParkingSlotManagerApplication.service;

import com.parkingsystem.ParkingSlotManagerApplication.model.User;
import com.parkingsystem.ParkingSlotManagerApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setContact(userDetails.getContact());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
