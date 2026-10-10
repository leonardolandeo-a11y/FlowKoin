package com.example.flowkoin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = false,length = 50)
    private String username;

    @Column(unique = true, nullable = false,length = 320)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, length = 3)
    private String defaultCurrency = "PEN";

    @Column(nullable = false)
    private String timeZone = "America/Lima";

    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<Bill> bills = new ArrayList<>();

    @PrePersist
    public void onCreate(){
        if ( registrationDate == null){
            registrationDate = LocalDateTime.now();
        }
    }



}
