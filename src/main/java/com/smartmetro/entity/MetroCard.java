package com.smartmetro.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "metro_card")
public class MetroCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;
    private double balance;
    private String cardNumber;
    private String status;
    private Date issueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
