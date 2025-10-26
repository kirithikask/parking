package com.parkingsystem.ParkingSlotManagerApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingLog;
import com.parkingsystem.ParkingSlotManagerApplication.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public List<ParkingLog> getAllLogs() {
        return logRepository.findAll();
    }

    public List<ParkingLog> getLogsByVehicle(Long vehicleId) {
        return logRepository.findByVehicleVehicleId(vehicleId);
    }

    public ParkingLog addLog(ParkingLog log) {
        return logRepository.save(log);
    }

    public ParkingLog updateLog(Long id, ParkingLog logDetails) {
        ParkingLog log = logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found with id: " + id));
        log.setVehicle(logDetails.getVehicle());
        log.setSlot(logDetails.getSlot());
        log.setEntryTime(logDetails.getEntryTime());
        log.setExitTime(logDetails.getExitTime());
        log.setStatus(logDetails.getStatus());
        log.setRemarks(logDetails.getRemarks());
        return logRepository.save(log);
    }

    public void deleteLog(Long id) {
        ParkingLog log = logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found with id: " + id));
        logRepository.delete(log);
    }
}
