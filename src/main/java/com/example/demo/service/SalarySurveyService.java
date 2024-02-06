package com.example.demo.service;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.mapper.SalarySurveyMapper;
import com.example.demo.repository.SalarySurveyRepository;
import com.example.demo.response.SalarySurveyDto;
import jakarta.persistence.EntityManager;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalarySurveyService {

    @Autowired
    private SalarySurveyRepository salarySurveyRepository;

    private SalarySurveyMapper salarySurveyMapper = Mappers.getMapper( SalarySurveyMapper.class );

    public List<SalarySurveyDto> getSalarySurvey(Sort sortable, List<String> fields) {
        List<SalarySurveyEntity> salarySurveyEntities = salarySurveyRepository.findAll(sortable);
        return mapToDto(salarySurveyEntities, fields);
    }

    private List<SalarySurveyDto> mapToDto(List<SalarySurveyEntity> surveys, List<String> fields) {
        if (fields == null || fields.isEmpty()) {
            return salarySurveyMapper.salarySurveyToSalarySurveyDto(surveys);
        }
        return surveys.stream().map(survey -> {
            SalarySurveyDto dto = new SalarySurveyDto();
                for (String fieldName : fields) {
                    try {
                        Field field = SalarySurveyEntity.class.getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Object value = field.get(survey);
                        Field dtoField = SalarySurveyDto.class.getDeclaredField(fieldName);
                        dtoField.setAccessible(true);
                        dtoField.set(dto, value);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        // Handle exception or log error
                    }
                }
            return dto;
        }).collect(Collectors.toList());
    }
}
