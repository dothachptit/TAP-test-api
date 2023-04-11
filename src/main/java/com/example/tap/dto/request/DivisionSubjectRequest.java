package com.example.tap.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionSubjectRequest {
    private Integer divisionId;
    private Integer subjectId;
}
