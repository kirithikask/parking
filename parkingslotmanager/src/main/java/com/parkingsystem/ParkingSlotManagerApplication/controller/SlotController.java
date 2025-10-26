package com.parkingsystem.ParkingSlotManagerApplication.controller;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingSlot;
import com.parkingsystem.ParkingSlotManagerApplication.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    // Get all slots
    @GetMapping
    public List<ParkingSlot> getAllSlots() {
        return slotService.getAllSlots();
    }

    // Add a new slot
    @PostMapping("/addSlot")
    public ParkingSlot addSlot(@RequestBody ParkingSlot slot) {
        return slotService.addSlot(slot);
    }

    // Assign a vehicle to a slot
    @PostMapping("/{slotId}/assign/{vehicleId}")
    public String assignSlot(@PathVariable Long slotId, @PathVariable Long vehicleId) {
        return slotService.assignSlot(slotId, vehicleId);
    }

    // Update slot info
    @PutMapping("/update/{id}")
    public ParkingSlot updateSlot(@PathVariable Long id, @RequestBody ParkingSlot slotDetails) {
        return slotService.updateSlot(id, slotDetails);
    }
}
