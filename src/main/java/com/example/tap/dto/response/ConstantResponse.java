package com.example.tap.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstantResponse {
    private Integer id;
    private String name;
    private Integer value;
}
