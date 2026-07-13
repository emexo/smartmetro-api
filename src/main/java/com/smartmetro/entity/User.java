package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    //@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    private String name;

    private String email;

    private String phone;

    private String password;

    private String role;

    @Column(name="created_at")
    private String createdAt;

    @OneToMany(mappedBy = "user")
    private Set<MetroCard> metroCards;

}
