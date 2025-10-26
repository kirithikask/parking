package com.parkingsystem.ParkingSlotManagerApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingSlot;
import com.parkingsystem.ParkingSlotManagerApplication.repository.SlotRepository;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    public ParkingSlot addSlot(ParkingSlot slot) {
        return slotRepository.save(slot);
    }

    public ParkingSlot updateSlot(Long id, ParkingSlot slotDetails) {
        ParkingSlot slot = slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + id));
        slot.setSlotNumber(slotDetails.getSlotNumber());
        slot.setType(slotDetails.getType());
        slot.setStatus(slotDetails.getStatus());
        slot.setFloor(slotDetails.getFloor());
        return slotRepository.save(slot);
    }

    public String assignSlot(Long slotId, Long vehicleId) {
        ParkingSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + slotId));
        if (!"Available".equalsIgnoreCase(slot.getStatus())) {
            return "Slot is not available";
        }
        slot.setStatus("Occupied");
        slotRepository.save(slot);
        return "Vehicle " + vehicleId + " assigned to slot " + slot.getSlotNumber();
    }

    public void deleteSlot(Long id) {
        ParkingSlot slot = slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + id));
        slotRepository.delete(slot);
    }
}
