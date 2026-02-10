package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="contact_msg")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator= "native")
    private int contactId;
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
    private String status;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
