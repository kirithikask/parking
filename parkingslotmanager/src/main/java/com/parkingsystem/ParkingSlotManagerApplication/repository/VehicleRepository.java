package com.parkingsystem.ParkingSlotManagerApplication.repository;

import com.parkingsystem.ParkingSlotManagerApplication.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUserUserId(Long userId);
}
