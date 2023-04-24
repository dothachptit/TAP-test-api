package com.example.tap.service.implement;

import com.example.tap.constant.ErrorMessage;
import com.example.tap.dto.request.ExamineeRequest;
import com.example.tap.dto.request.IStudentExamineeRequest;
import com.example.tap.dto.response.ExamineeResponse;
import com.example.tap.dto.response.IStudentExamineeResponse;
import com.example.tap.entity.DivisionSubject;
import com.example.tap.entity.Examinee;
import com.example.tap.entity.ExamineeOfIStudent;
import com.example.tap.entity.ExamineeSubject;
import com.example.tap.exception.BadRequestException;
import com.example.tap.repository.*;
import com.example.tap.service.ExamineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamineeServiceImpl implements ExamineeService {
    private final SubjectRepo subjectRepo;
    private final DivisionRepo divisionRepo;
    private final ExamineeSubjectRepo examineeSubjectRepo;
    private final ExamineeRepo examineeRepo;
    private final ConstantEntityRepo constantEntityRepo;
    private final ExamineeOfIStudentRepo examineeOfIStudentRepo;

    @Override
    public ExamineeResponse calculateScoreOfExaminee(ExamineeRequest examineeRequest) {
        Examinee examinee = new Examinee();
        if(!divisionRepo.findByAcronym(examineeRequest.getAcronymOfDivision()).isPresent()){
            throw new BadRequestException(ErrorMessage.ACRONYM_IS_NOT_PRESENT);
        }
        examinee.setAcronymOfDivision(examineeRequest.getAcronymOfDivision());
        examineeRepo.save(examinee);

        List<ExamineeSubject> examineeSubjects = new ArrayList<>();
        examineeRequest.getExamineeSubjectDtos()
                .forEach(i -> {
                    ExamineeSubject examineeSubject = new ExamineeSubject();
                    if(!subjectRepo.findById(i.getSubjectId()).isPresent()){
                        throw new BadRequestException(ErrorMessage.SUBJECT_IS_NOT_PRESENT);
                    }
                    examineeSubject.setSubjectid(subjectRepo.findById(i.getSubjectId()).get());
                    examineeSubject.setScore(i.getScore());
                    examineeSubject.setExamineeid(examinee);
                    examineeSubjects.add(examineeSubject);
                });

        examineeSubjectRepo.saveAll(examineeSubjects);
        examinee.setExamineeSubjects(examineeSubjects);
        examinee.getExamineeSubjects()
                .forEach(i -> {
                    examinee.setTotalScore(examinee.getTotalScore() + i.getScore());
                    int check = 0;
                    for (DivisionSubject ds : i.getSubjectid().getDivisionSubjects()) {

                        if (ds.getDivisionid().getAcronym().equals(examinee.getAcronymOfDivision())) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) examinee.setDivisionScore(examinee.getDivisionScore() + i.getScore());
                });

        if (examinee.getTotalScore() >= constantEntityRepo.findByName("TOTAL_SCORE").get().getValue() &&
                examinee.getDivisionScore() >= divisionRepo.findByAcronym(examinee.getAcronymOfDivision()).get().getMinDivisionScore())
            examinee.setStatus("Pass");
        else examinee.setStatus("Fail");
        examineeRepo.save(examinee);
        ExamineeResponse examineeResponse = new ExamineeResponse();
        examineeResponse.setExaminee(examinee);
        return examineeResponse;
    }

    @Override
    public IStudentExamineeResponse calculateScoreOfIStudentExaminee(IStudentExamineeRequest iStudentExamineeRequest) {
       ExamineeOfIStudent examineeOfIStudent = new ExamineeOfIStudent();
        examineeOfIStudent.setAcronymOfDivision(iStudentExamineeRequest.getAcronymOfDivision());
        examineeRepo.save(examineeOfIStudent);
        List<ExamineeSubject> examineeSubjects = new ArrayList<>();
        iStudentExamineeRequest.getExamineeSubjectDtos()
                .forEach(i -> {
                    ExamineeSubject examineeSubject = new ExamineeSubject();
                    examineeSubject.setSubjectid(subjectRepo.findById(i.getSubjectId()).get());
                    examineeSubject.setScore(i.getScore());
                    examineeSubject.setExamineeid(examineeOfIStudent);
                    examineeSubjects.add(examineeSubject);
                });
        examineeSubjectRepo.saveAll(examineeSubjects);
        examineeOfIStudent.setExamineeSubjects(examineeSubjects);
        examineeOfIStudent.getExamineeSubjects()
                .forEach(i -> {
                    examineeOfIStudent.setTotalScore(examineeOfIStudent.getTotalScore() + i.getScore());
                    int check = 0;
                    for (DivisionSubject ds : i.getSubjectid().getDivisionSubjects()) {

                        if (ds.getDivisionid().getAcronym().equals(examineeOfIStudent.getAcronymOfDivision())) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) examineeOfIStudent.setDivisionScore(examineeOfIStudent.getDivisionScore() + i.getScore());
                });
        examineeOfIStudent.getExamineeSubjects()
                .forEach(i ->{
                    int check = 0;
                    String name = "Science";
                    for (DivisionSubject ds : i.getSubjectid().getDivisionSubjects()){
                        if(ds.getDivisionid().getAcronym().equals(divisionRepo.findByName(name).get().getAcronym())){
                            check=1;
                            break;
                        }
                    }
                    if (check==0){
                        examineeOfIStudent.setStudentScore(examineeOfIStudent.getStudentScore()*i.getScore());
                    }
                    examineeOfIStudent.setStudentScore(examineeOfIStudent.getStudentScore()/2);
                });

        if (examineeOfIStudent.getTotalScore() >= constantEntityRepo.findByName("TOTAL_SCORE").get().getValue() &&
                examineeOfIStudent.getDivisionScore() >= divisionRepo.findByAcronym(examineeOfIStudent.getAcronymOfDivision()).get().getMinDivisionScore())
            examineeOfIStudent.setStatus("Pass");
        else examineeOfIStudent.setStatus("Fail");
        examineeRepo.save(examineeOfIStudent);
       examineeOfIStudent.setNational(iStudentExamineeRequest.getNationnal());
       IStudentExamineeResponse iStudentExamineeResponse=new IStudentExamineeResponse();
       examineeOfIStudentRepo.save(examineeOfIStudent);
       return iStudentExamineeResponse;

    }
}
