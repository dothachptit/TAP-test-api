package com.example.tap.service;

import com.example.tap.dto.request.ExamineeRequest;
import com.example.tap.dto.response.ExamineeResponse;

public interface ExamineeService {

    ExamineeResponse calculateScoreOfExaminee(ExamineeRequest examineeRequest);
}
