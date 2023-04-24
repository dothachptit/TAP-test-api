package com.example.tap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examineeofinternationalstudent")
public class ExamineeOfIStudent extends Examinee{
    @Column(name = "student_score")
    private Integer studentScore = 1;

    @Column(name = "national")
    private String national;

}
