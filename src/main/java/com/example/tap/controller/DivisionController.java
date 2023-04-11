package com.example.tap.controller;

import com.example.tap.dto.request.DivisionRequest;
import com.example.tap.dto.request.DivisionSubjectRequest;
import com.example.tap.service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/division")
@RequiredArgsConstructor
public class DivisionController {
    private final DivisionService divisionService;

    @PostMapping("/add-division")
    public ResponseEntity<?> addDivision(@RequestBody DivisionRequest divisionRequest) {
        return ResponseEntity.ok(divisionService.addDivision(divisionRequest));

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllDivision() {
        return ResponseEntity.ok(divisionService.getAllDivision());
    }

    @PostMapping("/add-subject-dto-division")
    public ResponseEntity<?> addSubjectToDivision(@RequestBody DivisionSubjectRequest divisionSubjectRequest) {
        divisionService.addSubjectToDivision(divisionSubjectRequest);
        return ResponseEntity.ok("Created");
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok(divisionService.deleteById(id));
    }
}
