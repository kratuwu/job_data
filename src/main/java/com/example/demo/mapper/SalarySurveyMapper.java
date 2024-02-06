package com.example.demo.mapper;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.response.SalarySurveyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SalarySurveyMapper {

    List<SalarySurveyDto> salarySurveyToSalarySurveyDto(List<SalarySurveyEntity> salarySurveyEntity);
}
