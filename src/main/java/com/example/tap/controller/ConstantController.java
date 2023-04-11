package com.example.tap.controller;

import com.example.tap.dto.request.ConstantRequest;
import com.example.tap.service.ConstantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/constant")
@RequiredArgsConstructor
public class ConstantController {
    private final ConstantService constantService;
    @PostMapping("/add-constant")
    public ResponseEntity<?> addConstant(@RequestBody ConstantRequest constantRequest){
        return ResponseEntity.ok(constantService.addConstant(constantRequest));
    }
    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<?> updateConstant(@RequestBody ConstantRequest constantRequest, @PathVariable Integer id){
        return ResponseEntity.ok(constantService.updateById(constantRequest,id));
    }
    @GetMapping("/find-all")
    public ResponseEntity<?> getAllConstant(){
        return ResponseEntity.ok(constantService.getAllConstant());
    }
}
