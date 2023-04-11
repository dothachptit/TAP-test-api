package com.example.tap.controller;

import com.example.tap.dto.request.SubjectRequest;
import com.example.tap.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/add-subject")
    public ResponseEntity<?> addSubject(@RequestBody SubjectRequest subjectRequest) {
        return ResponseEntity.ok(subjectService.addSubject(subjectRequest));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllSubject(){
        return ResponseEntity.ok(subjectService.getAllSubject());
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteSubjectById(@PathVariable Integer id){
        return ResponseEntity.ok(subjectService.deleteById(id));
    }
}
