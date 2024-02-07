package com.example.demo.utils;

import com.example.demo.entity.SalarySurveyEntity;
import com.example.demo.mapper.SalarySurveyMapper;
import lombok.experimental.UtilityClass;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class FieldFilterUtil {
    private SalarySurveyMapper salarySurveyMapper = Mappers.getMapper(SalarySurveyMapper.class);

    public static List<Object> filterByFields(List<SalarySurveyEntity> salarySurveyEntities, List<String> fields) {
        if (fields == null || fields.isEmpty()) {
            return (List<Object>) (List<?>) salarySurveyMapper.salarySurveyToSalarySurveyDto(salarySurveyEntities);
        }
        return salarySurveyEntities.stream().map(survey -> {
            Map map = new HashMap();
            for (String fieldName : fields) {
                try {
                    Field field = SalarySurveyEntity.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    map.put(fieldName, field.get(survey));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                }
            }
            return map;
        }).collect(Collectors.toList());
    }
}
