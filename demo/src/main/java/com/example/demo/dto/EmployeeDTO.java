package com.example.demo.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    private int age;

    private String name;

    private String email;

    private LocalDate dateOfJoining;

    private boolean isActive;
}
