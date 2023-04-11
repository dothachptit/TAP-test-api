package com.example.tap.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionResponse {
    private  Integer id;
    private String name;
    private Integer minDivisionScore;
    private String acronym;
    List<SubjectResponse> subjects;
}


