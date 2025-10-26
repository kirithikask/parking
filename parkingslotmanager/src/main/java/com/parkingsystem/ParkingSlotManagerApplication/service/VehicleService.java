package com.parkingsystem.ParkingSlotManagerApplication.service;

import com.parkingsystem.ParkingSlotManagerApplication.model.Vehicle;
import com.parkingsystem.ParkingSlotManagerApplication.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getVehiclesByUser(Long userId) {
        return vehicleRepository.findByUserUserId(userId);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        vehicle.setType(updatedVehicle.getType());
        vehicle.setModel(updatedVehicle.getModel());
        return vehicleRepository.save(vehicle);
    }

    public String deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
        return "Vehicle deleted successfully";
    }
}
