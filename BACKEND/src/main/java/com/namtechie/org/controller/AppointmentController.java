package com.namtechie.org.controller;

import com.namtechie.org.entity.Doctor;
import com.namtechie.org.entity.ServiceType;
import com.namtechie.org.entity.Zone;
import com.namtechie.org.model.Schedule;
import com.namtechie.org.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@SecurityRequirement(name = "api")
@CrossOrigin(origins = "http://localhost:5741")
public class AppointmentController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ZoneService zoneService;

    @Autowired
    ServiceTypesService serviceTypesService;

    @Autowired
    TokenService tokenService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/getFreeScheduleByDoctorId", produces = "application/json")
    public ResponseEntity<Map<String, List<Schedule>>> getFreeScheduleByDoctorId(@Valid @RequestHeader("AuthenticationToken") String authorizationHeader, @RequestParam long doctorId) {
        String token = tokenService.getToken(authorizationHeader);
        if (tokenService.getAccountByToken(token) != null) {
            return ResponseEntity.ok(scheduleService.findFreeScheduleByVeterianId(doctorId));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/getFreeScheduleWithTime", produces = "application/json")
    public ResponseEntity<Map<String, List<Schedule>>> getFreeScheduleWithTime(@Valid @RequestHeader("AuthenticationToken") String authorizationHeader) {
        String token = tokenService.getToken(authorizationHeader);
        if (tokenService.getAccountByToken(token) != null) {
            return ResponseEntity.ok(scheduleService.findFreeSchedule());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/testFreeScheduleWithTime", produces = "application/json")
    public ResponseEntity testFreeScheduleWithTime() {
        return ResponseEntity.ok(appointmentService.findAppointmentByDoctorIdAndBookingDateAndBookingTime((long)1, Date.valueOf("2024-10-16"), Time.valueOf("14:00:00")));
    }

    @GetMapping(value = "/getFreeSchedule", produces = "application/json")
    public ResponseEntity<Map<String, List<Schedule>>> getFreeSchedule(@Valid @RequestHeader("AuthenticationToken") String authorizationHeader) {
        String token = tokenService.getToken(authorizationHeader);
        if (tokenService.getAccountByToken(token) != null) {
            return ResponseEntity.ok(scheduleService.findFreeScheduleOfSession());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //Tạm thời bỏ lấy Zone ở đây.
    //Cái này còn hơi đắng đo mà chắc cần token mà
    @GetMapping(value = "/getAllZone", produces = "application/json")
    public ResponseEntity<List<Zone>> getAllZone(@Valid @RequestHeader("AuthenticationToken") String authorizationHeader) {
        String token = tokenService.getToken(authorizationHeader);
        if (tokenService.getAccountByToken(token) != null) {
            return ResponseEntity.ok(zoneService.findAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    //Tạm thời bở nó ở đây đợi nó có nhà mới
    //Toi nghĩ Service Type thằng nào lấy xem chả được nhỉ?
    @GetMapping(value = "/getAllServiceType", produces = "application/json")
    public ResponseEntity<List<ServiceType>> getAllServiceType() {
        return ResponseEntity.ok(serviceTypesService.findAll());
    }

    //Nằm dỡ ní ơi
    @GetMapping("/getAllDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctor() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

}