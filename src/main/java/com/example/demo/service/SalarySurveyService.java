package com.example.demo.service;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.repository.SalarySurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalarySurveyService {

    @Autowired
    private SalarySurveyRepository salarySurveyRepository;

    public List<SalarySurveyEntity> getSalarySurvey(Sort sortable){
        return salarySurveyRepository.findAll(sortable);
    }
}
