package com.parkingsystem.ParkingSlotManagerApplication.controller;

import com.parkingsystem.ParkingSlotManagerApplication.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Current slot occupancy report
    @GetMapping("/occupancy")
    public Map<String, Object> getOccupancyReport() {
        return reportService.getOccupancyReport();
    }

    // Daily log report
    @GetMapping("/daily-logs")
    public List<?> getDailyLogs() {
        return reportService.getDailyLogs();
    }

}
