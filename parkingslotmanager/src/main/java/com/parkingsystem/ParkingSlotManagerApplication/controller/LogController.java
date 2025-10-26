package com.parkingsystem.ParkingSlotManagerApplication.controller;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingLog;
import com.parkingsystem.ParkingSlotManagerApplication.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    // Get all logs
    @GetMapping
    public List<ParkingLog> getAllLogs() {
        return logService.getAllLogs();
    }

    // Get logs for a vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public List<ParkingLog> getLogsByVehicle(@PathVariable Long vehicleId) {
        return logService.getLogsByVehicle(vehicleId);
    }

    // Add new log (entry/exit)
    @PostMapping("/addLog")
    public ParkingLog addLog(@RequestBody ParkingLog log) {
        return logService.addLog(log);
    }
}
