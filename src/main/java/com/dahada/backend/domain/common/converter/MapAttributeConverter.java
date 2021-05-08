package com.dahada.backend.domain.common.converter;

import com.dahada.backend.domain.common.utils.ConvertUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;

@Converter
public class MapAttributeConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        return ConvertUtil.stringify(attribute);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        return ConvertUtil.toMap(dbData);
    }
}
