package com.parkingsystem.ParkingSlotManagerApplication.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_logs")
public class ParkingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id")
    private ParkingSlot slot;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String status;   // Entry / Exit
    private String remarks;

    // getters and setters
    public Long getLogId() { return logId; }
    public void setLogId(Long logId) { this.logId = logId; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public ParkingSlot getSlot() { return slot; }
    public void setSlot(ParkingSlot slot) { this.slot = slot; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
