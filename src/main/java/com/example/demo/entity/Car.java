package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private Long licenseplate;
    @Column
    private String carname;
    @Column
    private String history;
}
