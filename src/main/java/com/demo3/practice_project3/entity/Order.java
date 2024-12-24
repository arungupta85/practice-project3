package com.demo3.practice_project3.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String items; // Simple representation of items, can be a JSON or a separate entity.
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private String status_api;

    private LocalDateTime createdAt;
}
