package com.example.tap.service.implement;

import com.example.tap.constant.ErrorMessage;
import com.example.tap.dto.request.DivisionRequest;
import com.example.tap.dto.request.DivisionSubjectRequest;
import com.example.tap.dto.response.DivisionResponse;

import com.example.tap.dto.response.SubjectResponse;
import com.example.tap.entity.Division;
import com.example.tap.entity.DivisionSubject;
import com.example.tap.entity.Subject;
import com.example.tap.exception.BadRequestException;
import com.example.tap.repository.DivisionRepo;
import com.example.tap.repository.DivisionSubjectRepo;
import com.example.tap.repository.SubjectRepo;
import com.example.tap.service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DivisionServiceImpl implements DivisionService {
    private final ModelMapper mapper;
    private final DivisionRepo divisionRepo;
    private final SubjectRepo subjectRepo;
    private final DivisionSubjectRepo divisionSubjectRepo;

    @Override
    public DivisionResponse addDivision(DivisionRequest divisionRequest) {
        Division division = new Division();
        if(divisionRepo.findByName(divisionRequest.getName()).isPresent() ){
            throw new BadRequestException(ErrorMessage.DIVISION_IS_PRESENT);
        }
        if(divisionRepo.findByAcronym(divisionRequest.getAcronym()).isPresent()){
            throw new BadRequestException(ErrorMessage.ACRONYM_IS_PRESENT);
        }
        division.setName(divisionRequest.getName());
        division.setMinDivisionScore(divisionRequest.getMinDivisionScore());
        division.setAcronym(divisionRequest.getAcronym());
        return mapper.map(divisionRepo.save(division), DivisionResponse.class);
    }

    @Override
    public List<DivisionResponse> getAllDivision() {
        return divisionRepo.findAll().stream().map(i -> {
                    DivisionResponse divisionResponse = mapper.map(i, DivisionResponse.class);
                    divisionResponse.setSubjects(
                            i.getSubjects().stream().map(e ->
                                            mapper.map(e.getSubjectid(), SubjectResponse.class))
                                    .collect(Collectors.toList())
                    );
                    return divisionResponse;
                }).
                collect(Collectors.toList());
    }

    @Override
    public void addSubjectToDivision(DivisionSubjectRequest divisionSubjectRequest) {
        if(divisionRepo.findById(divisionSubjectRequest.getDivisionId()).isPresent()){
            throw new BadRequestException(ErrorMessage.DIVISION_IS_NOT_PRESENT);
        }
        if(subjectRepo.findById(divisionSubjectRequest.getSubjectId()).isPresent()){
            throw new BadRequestException(ErrorMessage.SUBJECT_IS_NOT_PRESENT);
        }
        Division division = divisionRepo.findById(divisionSubjectRequest.getDivisionId()).get();
        Subject subject = subjectRepo.findById(divisionSubjectRequest.getSubjectId()).get();
        DivisionSubject divisionSubject = new DivisionSubject();
        divisionSubject.setDivisionid(division);
        divisionSubject.setSubjectid(subject);
        divisionSubjectRepo.save(divisionSubject);
    }

    @Override
    public Boolean deleteById(Integer id) {
        if(!divisionRepo.findById(id).isPresent()){
            throw new BadRequestException(ErrorMessage.DIVISION_IS_NOT_PRESENT);
        }
        divisionRepo.deleteById(id);
        return true;
    }
}
