package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.security.Timestamp;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "SALARY_SURVEY")
public class SalarySurveyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Timestamp")
    private String timestamp;

    @Column(name = "Employer")
    private String employer;

    @Column(name = "Location")
    private String location;

    @Column(name = "Job Title")
    private String jobTitle;

    @Column(name = "Years at Employer")
    private String yearsAtEmployer;

    @Column(name = "Years of Experience")
    private String yearsOfExperience;

    @Column(name = "Salary")
    private String salary;

    @Column(name = "Signing Bonus")
    private String signingBonus;

    @Column(name = "Annual Bonus")
    private String annualBonus;

    @Column(name = "Annual Stock Value/Bonus")
    private String annualStockValueBonus;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Additional Comments")
    private String additionalComments;

}
