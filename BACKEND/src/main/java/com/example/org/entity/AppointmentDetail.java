package com.example.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Data
public class AppointmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    @JsonBackReference
    private Appointment appointment;

    @Column
    private String address;

    @Column(nullable = false)
    private Date appointmentBookingDate;

    @Column(nullable = false)
    private Time appointmentBookingTime;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(nullable = false)
    private String descriptions;
}
