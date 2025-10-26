package com.parkingsystem.ParkingSlotManagerApplication.repository;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogRepository extends JpaRepository<ParkingLog, Long> {
    List<ParkingLog> findByVehicleVehicleId(Long vehicleId);
}
