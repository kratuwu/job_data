package com.example.demo.controller;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.response.SalarySurveyDto;
import com.example.demo.service.SalarySurveyService;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/salary-surveys")
public class SalarySurveyController {

    @Autowired
    private SalarySurveyService salarySurveyService;

    @GetMapping
    public ResponseEntity<List<SalarySurveyDto>> getJobDataList(
                                                @RequestParam(name = "fields", defaultValue = "") String fields,
                                                @RequestParam(defaultValue = "timestamp") String sort,
                                                @RequestParam(defaultValue = "ASC") String sort_type) {
        String camelCaseSort = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, sort);
        Sort.Direction direction = sort_type.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sortable = Sort.by(direction, camelCaseSort);
        List<SalarySurveyDto>  jobDataList = salarySurveyService.getSalarySurvey(sortable, List.of(fields.split(","))
                .stream().map(field->CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field))
                .toList());

        return new ResponseEntity<>(jobDataList, HttpStatus.OK);
    }
}
