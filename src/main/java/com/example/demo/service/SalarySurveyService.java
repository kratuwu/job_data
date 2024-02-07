package com.example.demo.service;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.repository.SalarySurveyRepository;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.demo.utils.FieldFilterUtil.filterByFields;
import static java.util.Objects.nonNull;

@Service
public class SalarySurveyService {

    @Autowired
    private SalarySurveyRepository salarySurveyRepository;

    public List<Object> getSortingSalarySurvey(String sort, String sortType) {

        String camelCaseSort = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, sort);
        Sort.Direction direction = sortType.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortable = Sort.by(direction, camelCaseSort);

        List<SalarySurveyEntity> salarySurveyEntities = salarySurveyRepository.findAll(sortable);
        return filterByFields(salarySurveyEntities, List.of());
    }

    public List<Object> getFilteringFieldsSalarySurvey(List<String> fields) {
        List<SalarySurveyEntity> salarySurveyEntities = salarySurveyRepository.findAll();
        return filterByFields(salarySurveyEntities, fields);
    }

    public List<Object> getFilteringSalarySurvey(String gender, String jobTitle, Double minSalary, Double maxSalary) {
        if (minSalary != null || maxSalary != null) {
            return filterSalaryLength(minSalary, maxSalary);
        } else {
            return filterGenderAndJobTitle(gender, jobTitle);
        }
    }

    private List<Object> filterSalaryLength(Double minSalary, Double maxSalary){
//        TODO: Uncomment after clean up data for salary to be type double
//        List<SalarySurveyEntity> bySalaryBetween;
//        if (minSalary != null && maxSalary != null) {
//            bySalaryBetween = salarySurveyRepository.findBySalaryBetween(minSalary, maxSalary);
//        } else if (minSalary != null) {
//            bySalaryBetween =  salarySurveyRepository.findBySalaryGreaterThanEqual(minSalary);
//        } else {
//            bySalaryBetween =  salarySurveyRepository.findBySalaryLessThanEqual(maxSalary);
//        }
        return filterByFields(salarySurveyRepository.findAll(), List.of("salary"));
    }

    private List<Object> filterGenderAndJobTitle(String gender, String jobTitle){
        SalarySurveyEntity salarySurvey = new SalarySurveyEntity();
        List<String> fields = List.of();
        if(nonNull(gender)){
            salarySurvey.setGender(gender);
            fields.add("gender");
        }
        if(nonNull(jobTitle)){
            salarySurvey.setGender(jobTitle);
            fields.add("jobTitle");
        }
        Example<SalarySurveyEntity> example = Example.of(salarySurvey, ExampleMatcher.matchingAny());
        return filterByFields(salarySurveyRepository.findAll(example), fields);
    }
}
