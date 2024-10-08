package com.namtechie.org.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestDTO {
    private Long accountID;
    private String fullNameCustomer;
    private Long veterianID; // Chỉ lấy ID
    private String serviceType;
    private Date preferredDate;
    private String phone;
    private String address;
}
