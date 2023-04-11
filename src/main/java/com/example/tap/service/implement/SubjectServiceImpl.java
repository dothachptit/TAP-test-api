package com.example.tap.service.implement;

import com.example.tap.constant.ErrorMessage;
import com.example.tap.dto.request.SubjectRequest;
import com.example.tap.dto.response.SubjectResponse;
import com.example.tap.entity.Subject;
import com.example.tap.exception.BadRequestException;
import com.example.tap.repository.SubjectRepo;
import com.example.tap.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final ModelMapper mapper;
    private final SubjectRepo subjectRepo;

    @Override
    public SubjectResponse addSubject(SubjectRequest subjectRequest) {
        Subject subject = new Subject();
        if(subjectRepo.findByName(subjectRequest.getName()).isPresent()){
            throw new BadRequestException(ErrorMessage.SUBJECT_IS_PRESENT);

        }

        subject.setName(subjectRequest.getName());
        return mapper.map(subjectRepo.save(subject), SubjectResponse.class);

    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        return subjectRepo.findAll().stream().map(i -> mapper.map(i, SubjectResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteById(Integer id) {
        if(subjectRepo.findById(id).isPresent()){
            throw new BadRequestException(ErrorMessage.SUBJECT_IS_NOT_PRESENT);
        }
        subjectRepo.deleteById(id);
        return true;
    }
}
