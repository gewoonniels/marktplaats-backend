package org.example.util;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return value != null ? (value ? "TRUE" : "FALSE") : "";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        boolean result = false;
        switch (s) {
            case "TRUE":
                result = true;
                break;
            case "FALSE":
                result = false;
                break;
        }
        return result;
    }
}
