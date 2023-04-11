package com.example.tap.service;

import com.example.tap.dto.request.ConstantRequest;
import com.example.tap.dto.response.ConstantResponse;

import java.util.List;

public interface ConstantService {
    ConstantResponse addConstant(ConstantRequest constantRequest);

    ConstantResponse updateById(ConstantRequest constantRequest, Integer id);

    List<ConstantResponse> getAllConstant();
}
