package com.example1.springWeek2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

//Other validate annotations: NotBlank, Email, Positive, Digits(integer=6, fraction=2), DecimalMax(value="100.44"),
//DecimalMin(value="22.5"), PastOrPresent, AssertTrue
//Pattern(regexp="^(ADMIN|USER)$"


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

//    @NotNull(message="provide value")
//    @Max(value=80, message="Age of Employee is greater")
//    @Min(value=15)
    private Integer age;

    private String name;

    private String email;

    private LocalDate dateOfJoining;

//    @JsonProperty("isActive")
    private boolean isActive;
}
