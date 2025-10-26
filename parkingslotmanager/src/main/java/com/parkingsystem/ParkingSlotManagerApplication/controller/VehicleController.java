package com.parkingsystem.ParkingSlotManagerApplication.controller;

import com.parkingsystem.ParkingSlotManagerApplication.model.Vehicle;
import com.parkingsystem.ParkingSlotManagerApplication.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Get all vehicles
    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getAllVehicles();
    }

    // Get vehicles by user
    @GetMapping("/user/{userId}")
    public List<Vehicle> getVehiclesByUser(@PathVariable Long userId) {
        return vehicleService.getVehiclesByUser(userId);
    }

    // Add a new vehicle
    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    // Update vehicle
    @PutMapping("/update/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.updateVehicle(id, vehicleDetails);
    }

    // Delete vehicle
    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }
}
