package com.example.demo.service;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.mapper.SalarySurveyMapper;
import com.example.demo.repository.SalarySurveyRepository;
import com.example.demo.response.SalarySurveyDto;
import com.google.common.base.CaseFormat;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    public List<SalarySurveyDto> getSortingSalarySurvey(String sort, String sortType) {

        String camelCaseSort = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, sort);
        Sort.Direction direction = sortType.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortable = Sort.by(direction, camelCaseSort);

        List<SalarySurveyEntity> salarySurveyEntities = salarySurveyRepository.findAll(sortable);
        return salarySurveyMapper.salarySurveyToSalarySurveyDto(salarySurveyEntities);
    }

    public List<SalarySurveyDto> getFilteringFieldsSalarySurvey(List<String> fields) {

        List<SalarySurveyEntity> salarySurveyEntities = salarySurveyRepository.findAll();
        return mapToDto(salarySurveyEntities, fields);
    }

    public List<SalarySurveyDto> getFilteringSalarySurvey(String gender, String jobTitle) {
        SalarySurveyEntity salarySurvey = new SalarySurveyEntity();
        salarySurvey.setGender(gender);
        salarySurvey.setJobTitle(jobTitle);
        Example<SalarySurveyEntity> example = Example.of(salarySurvey, ExampleMatcher.matchingAny());
        List<SalarySurveyEntity> salarySurveyEntities = salarySurveyRepository.findAll(example);
        return salarySurveyMapper.salarySurveyToSalarySurveyDto(salarySurveyEntities);
    }

    private List<SalarySurveyDto> mapToDto(List<SalarySurveyEntity> salarySurveyEntities, List<String> fields) {
        if (fields == null || fields.isEmpty()) {
            return salarySurveyMapper.salarySurveyToSalarySurveyDto(salarySurveyEntities);
        }
        return salarySurveyEntities.stream().map(survey -> {
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
                    }
                }
            return dto;
        }).collect(Collectors.toList());
    }
}
