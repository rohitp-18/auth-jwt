package com.app.securityspring.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public  String password;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public LocalDate createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    public  LocalDate updatedAt;
}
