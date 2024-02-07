package com.example.demo.controller;

import com.example.demo.service.SalarySurveyService;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "/salary-surveys")
public class SalarySurveyController {

    @Autowired
    private SalarySurveyService salarySurveyService;

    @GetMapping
    public ResponseEntity<List<Object>> getJobDataList(
                                                @RequestParam(name = "fields", defaultValue = "", required = false) String fields,
                                                @RequestParam(defaultValue = "timestamp", required = false) String sort,
                                                @RequestParam(value = "sor_type", defaultValue = "ASC", required = false) String sortType,
                                                @RequestParam(value = "gender", required = false) String gender,
                                                @RequestParam(value = "job_title", required = false) String jobTitle,
                                                @RequestParam(required = false) Double minSalary,
                                                @RequestParam(required = false) Double maxSalary) {

        List<Object>  jobDataList;

        if(nonNull(jobTitle) || nonNull(gender)){
            jobDataList = salarySurveyService.getFilteringSalarySurvey(sort, sortType, minSalary, maxSalary);
        } else if(nonNull(fields)) {
            List<String> camelFields = List.of(fields.split(",")).stream().map(field -> CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field)).toList();
            jobDataList = salarySurveyService.getFilteringFieldsSalarySurvey(camelFields);
        } else {
            jobDataList = salarySurveyService.getSortingSalarySurvey(sort, sortType);
        }

        return new ResponseEntity<>(jobDataList, HttpStatus.OK);
    }
}
