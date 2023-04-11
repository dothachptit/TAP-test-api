package com.example.tap.service;

import com.example.tap.dto.request.DivisionRequest;
import com.example.tap.dto.request.DivisionSubjectRequest;
import com.example.tap.dto.response.DivisionResponse;

import java.util.List;

public interface DivisionService {

    DivisionResponse addDivision(DivisionRequest divisionRequest);

    List<DivisionResponse> getAllDivision();

    void addSubjectToDivision(DivisionSubjectRequest divisionSubjectRequest);

    Boolean deleteById(Integer id);
}
