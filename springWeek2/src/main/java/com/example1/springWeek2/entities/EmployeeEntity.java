package com.example1.springWeek2.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int age;
    private String name;
    private String email;
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private boolean isActive;

    //public void setId(Long id) {this.id = id;}

}
