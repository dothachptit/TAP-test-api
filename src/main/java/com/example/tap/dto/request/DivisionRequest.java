package com.example.tap.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionRequest {
    private String name;
    private Integer minDivisionScore;
    private String acronym;
}
