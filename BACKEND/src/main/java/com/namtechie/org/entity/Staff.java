package com.namtechie.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.namtechie.org.entity.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffID;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String phone;

    @Column
    private Boolean sex;

}
