package com.parkingsystem.ParkingSlotManagerApplication.repository;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<ParkingSlot, Long> {
}
