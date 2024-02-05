package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "SALARY_SURVEY")
@Getter
public class SalarySurveyEntity {
    @Id
    Integer id;

    @Column(name = "Timestamp")
    String timestamp;

    @Column(name = "Employer")
    String employer;

    @Column(name = "Location")
    String location;

    @Column(name = "Job Title")
    String jobTitle;

    @Column(name = "Years at Employer")
    String yearsAtEmployer;

    @Column(name = "Years of Experience")
    String yearsOfExperience;

    @Column(name = "Salary")
    String salary;

    @Column(name = "Signing Bonus")
    String signingBonus;

    @Column(name = "Annual Bonus")
    String annualBonus;

    @Column(name = "Annual Stock Value/Bonus")
    String annualStockValueBonus;

    @Column(name="Gender")
    String gender;

    @Column(name="Additional Comments")
    String additionalComments;

}
