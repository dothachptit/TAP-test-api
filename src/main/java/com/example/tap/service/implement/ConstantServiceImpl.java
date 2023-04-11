package com.example.tap.service.implement;

import com.example.tap.constant.ErrorMessage;
import com.example.tap.dto.request.ConstantRequest;
import com.example.tap.dto.response.ConstantResponse;
import com.example.tap.entity.ConstantEntity;
import com.example.tap.exception.BadRequestException;
import com.example.tap.repository.ConstantEntityRepo;
import com.example.tap.service.ConstantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConstantServiceImpl implements ConstantService {
    private final ModelMapper mapper;
    private final ConstantEntityRepo constantEntityRepo;
    @Override
    public ConstantResponse addConstant(ConstantRequest constantRequest) {
        ConstantEntity constantEntity = new ConstantEntity();
        if(constantEntityRepo.findByName(constantRequest.getName()).isPresent()){
            throw new BadRequestException(ErrorMessage.CONSTANT_IS_PRESENT);
        }
        constantEntity.setName(constantRequest.getName());
        constantEntity.setValue(constantRequest.getValue());
        return mapper.map(constantEntityRepo.save(constantEntity),ConstantResponse.class);
    }

    @Override
    public ConstantResponse updateById(ConstantRequest constantRequest, Integer id) {
        if(!constantEntityRepo.findById(id).isPresent()){
            throw new BadRequestException(ErrorMessage.CONSTANT_IS_NOT_PRESENT);
        }
        ConstantEntity constantEntity = constantEntityRepo.findById(id).get();
        constantEntity.setName(constantRequest.getName());
        constantEntity.setValue(constantRequest.getValue());
        return mapper.map(constantEntityRepo.save(constantEntity),ConstantResponse.class);
    }

    @Override
    public List<ConstantResponse> getAllConstant() {
        return constantEntityRepo.findAll().stream().map(i -> mapper.map(i,ConstantResponse.class)).collect(Collectors.toList());
    }
}
