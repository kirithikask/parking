package com.parkingsystem.ParkingSlotManagerApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    @NotBlank
    @Column(unique = true)
    private String slotNumber;

    private String type;   // Car / Bike / Reserved
    private String status; // Available / Occupied / Reserved
    private String floor;

    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ParkingLog> logs;

    // getters and setters
    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }

    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }

    public List<ParkingLog> getLogs() { return logs; }
    public void setLogs(List<ParkingLog> logs) { this.logs = logs; }
}
