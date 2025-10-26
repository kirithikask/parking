package com.parkingsystem.ParkingSlotManagerApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingLog;
import com.parkingsystem.ParkingSlotManagerApplication.model.ParkingSlot;
import com.parkingsystem.ParkingSlotManagerApplication.model.User;
import com.parkingsystem.ParkingSlotManagerApplication.model.Vehicle;
import com.parkingsystem.ParkingSlotManagerApplication.service.LogService;
import com.parkingsystem.ParkingSlotManagerApplication.service.ReportService;
import com.parkingsystem.ParkingSlotManagerApplication.service.SlotService;
import com.parkingsystem.ParkingSlotManagerApplication.service.UserService;
import com.parkingsystem.ParkingSlotManagerApplication.service.VehicleService;

@Controller
public class UIController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SlotService slotService;

    @Autowired
    private LogService logService;

    @Autowired
    private ReportService reportService;

    // ✅ Home page (index.html)
    @GetMapping("/")
    public String home() {
        return "index";  // maps to templates/index.html
    }

    // ✅ Users view page
    @GetMapping("/users/view")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user"; // templates/user.html
    }

    // ✅ Vehicles view page
    @GetMapping("/vehicles/view")
    public String viewVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "vehicles"; // templates/vehicles.html
    }

    // ✅ Slots view page
    @GetMapping("/slots/view")
    public String viewSlots(Model model) {
        model.addAttribute("slots", slotService.getAllSlots());
        return "slot"; // templates/slot.html
    }

    // ✅ Logs view page
    @GetMapping("/logs/view")
    public String viewLogs(Model model) {
        model.addAttribute("logs", logService.getAllLogs());
        return "logs"; // templates/logs.html
    }

    // ✅ Reports view page
    @GetMapping("/reports/view")
    public String viewReports(Model model) {
        model.addAttribute("report", reportService.getOccupancyReport());
        return "reports"; // templates/reports.html
    }

    // User CRUD
    @PostMapping("/users/add")
    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String contact, @RequestParam String role, RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setRole(role);
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", "User added successfully!");
        return "redirect:/users/view";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String email, @RequestParam String contact, @RequestParam String role, RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setRole(role);
        userService.updateUser(id, user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        return "redirect:/users/view";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/users/view";
    }

    // Vehicle CRUD
    @PostMapping("/vehicles/add")
    public String addVehicle(@RequestParam Long userId, @RequestParam String licensePlate, @RequestParam String type, @RequestParam String model, RedirectAttributes redirectAttributes) {
        Vehicle vehicle = new Vehicle();
        User user = new User();
        user.setUserId(userId);
        vehicle.setUser(user);
        vehicle.setLicensePlate(licensePlate);
        vehicle.setType(type);
        vehicle.setModel(model);
        vehicleService.addVehicle(vehicle);
        redirectAttributes.addFlashAttribute("message", "Vehicle added successfully!");
        return "redirect:/vehicles/view";
    }

    @PostMapping("/vehicles/update/{id}")
    public String updateVehicle(@PathVariable Long id, @RequestParam Long userId, @RequestParam String licensePlate, @RequestParam String type, @RequestParam String model, RedirectAttributes redirectAttributes) {
        Vehicle vehicle = new Vehicle();
        User user = new User();
        user.setUserId(userId);
        vehicle.setUser(user);
        vehicle.setLicensePlate(licensePlate);
        vehicle.setType(type);
        vehicle.setModel(model);
        vehicleService.updateVehicle(id, vehicle);
        redirectAttributes.addFlashAttribute("message", "Vehicle updated successfully!");
        return "redirect:/vehicles/view";
    }

    @PostMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        vehicleService.deleteVehicle(id);
        redirectAttributes.addFlashAttribute("message", "Vehicle deleted successfully!");
        return "redirect:/vehicles/view";
    }

    // Slot CRUD
    @PostMapping("/slots/add")
    public String addSlot(@RequestParam String slotNumber, @RequestParam String type, @RequestParam String status, @RequestParam String floor, RedirectAttributes redirectAttributes) {
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(slotNumber);
        slot.setType(type);
        slot.setStatus(status);
        slot.setFloor(floor);
        slotService.addSlot(slot);
        redirectAttributes.addFlashAttribute("message", "Slot added successfully!");
        return "redirect:/slots/view";
    }

    @PostMapping("/slots/update/{id}")
    public String updateSlot(@PathVariable Long id, @RequestParam String slotNumber, @RequestParam String type, @RequestParam String status, @RequestParam String floor, RedirectAttributes redirectAttributes) {
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(slotNumber);
        slot.setType(type);
        slot.setStatus(status);
        slot.setFloor(floor);
        slotService.updateSlot(id, slot);
        redirectAttributes.addFlashAttribute("message", "Slot updated successfully!");
        return "redirect:/slots/view";
    }

    @PostMapping("/slots/delete/{id}")
    public String deleteSlot(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        slotService.deleteSlot(id);
        redirectAttributes.addFlashAttribute("message", "Slot deleted successfully!");
        return "redirect:/slots/view";
    }

    // Log CRUD
    @PostMapping("/logs/add")
    public String addLog(@RequestParam Long vehicleId, @RequestParam Long slotId, @RequestParam String status, @RequestParam String remarks, RedirectAttributes redirectAttributes) {
        ParkingLog log = new ParkingLog();
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotId(slotId);
        log.setVehicle(vehicle);
        log.setSlot(slot);
        log.setEntryTime(java.time.LocalDateTime.now());
        log.setStatus(status);
        log.setRemarks(remarks);
        logService.addLog(log);
        redirectAttributes.addFlashAttribute("message", "Log added successfully!");
        return "redirect:/logs/view";
    }

    @PostMapping("/logs/update/{id}")
    public String updateLog(@PathVariable Long id, @RequestParam Long vehicleId, @RequestParam Long slotId, @RequestParam String status, @RequestParam String remarks, RedirectAttributes redirectAttributes) {
        ParkingLog log = new ParkingLog();
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotId(slotId);
        log.setVehicle(vehicle);
        log.setSlot(slot);
        log.setStatus(status);
        log.setRemarks(remarks);
        logService.updateLog(id, log);
        redirectAttributes.addFlashAttribute("message", "Log updated successfully!");
        return "redirect:/logs/view";
    }

    @PostMapping("/logs/delete/{id}")
    public String deleteLog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        logService.deleteLog(id);
        redirectAttributes.addFlashAttribute("message", "Log deleted successfully!");
        return "redirect:/logs/view";
    }

}
