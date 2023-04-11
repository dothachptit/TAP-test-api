package com.example.tap.service;

import com.example.tap.dto.request.SubjectRequest;
import com.example.tap.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {
    SubjectResponse addSubject(SubjectRequest subjectRequest);

    List<SubjectResponse> getAllSubject();

    Boolean deleteById(Integer id);
}
