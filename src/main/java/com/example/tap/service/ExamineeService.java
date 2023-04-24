package com.example.tap.service;

import com.example.tap.dto.request.ExamineeRequest;
import com.example.tap.dto.request.IStudentExamineeRequest;
import com.example.tap.dto.response.ExamineeResponse;
import com.example.tap.dto.response.IStudentExamineeResponse;

public interface ExamineeService {

    ExamineeResponse calculateScoreOfExaminee(ExamineeRequest examineeRequest);

    IStudentExamineeResponse calculateScoreOfIStudentExaminee(IStudentExamineeRequest iStudentExamineeRequest);
}
