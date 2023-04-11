package com.example.tap.dto.request;

import com.example.tap.dto.ExamineeSubjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamineeRequest {
    private String acronymOfDivision;
    private List<ExamineeSubjectDto> examineeSubjectDtos;
}