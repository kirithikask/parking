package com.parkingsystem.ParkingSlotManagerApplication.service;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingSlot;
import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingLog;
import com.parkingsystem.ParkingSlotManagerApplication.repository.SlotRepository;
import com.parkingsystem.ParkingSlotManagerApplication.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private LogRepository logRepository;

    public Map<String, Object> getOccupancyReport() {
        List<ParkingSlot> allSlots = slotRepository.findAll();
        long total = allSlots.size();
        long occupied = allSlots.stream().filter(s -> "Occupied".equalsIgnoreCase(s.getStatus())).count();
        long available = total - occupied;

        Map<String, Object> report = new HashMap<>();
        report.put("totalSlots", total);
        report.put("occupiedSlots", occupied);
        report.put("availableSlots", available);
        report.put("occupancyRate", total > 0 ? (occupied * 100.0 / total) + "%" : "0%");
        return report;
    }

    public List<ParkingLog> getDailyLogs() {
        return logRepository.findAll();
    }
}
