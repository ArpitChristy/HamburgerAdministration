package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="reservations")
@Data
public class Reservation {
    @Id
    private int reservationId;
    private String fullName;
    private int locationId;
    private String phone;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBooking;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfEvent;
    private String status;
}
