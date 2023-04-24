package com.example.tap.controller;

import com.example.tap.dto.request.ExamineeRequest;
import com.example.tap.dto.request.IStudentExamineeRequest;
import com.example.tap.repository.ExamineeRepo;
import com.example.tap.service.ExamineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examinee")
@RequiredArgsConstructor
public class ExamineeController {
    private final ExamineeService examineeService;
    @PostMapping
    public ResponseEntity<?> calculateScoreOfExaminee(@RequestBody ExamineeRequest examineeRequest) {
        return ResponseEntity.ok(examineeService.calculateScoreOfExaminee(examineeRequest));
    }
    @PostMapping("/international-student-examinnee")
    public ResponseEntity<?> calculateScoreOf(@RequestBody IStudentExamineeRequest iStudentExamineeRequest){
        return ResponseEntity.ok(examineeService.calculateScoreOfIStudentExaminee(iStudentExamineeRequest));
    }

}
