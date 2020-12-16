package org.example.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class BooleanConverterTest {
    BooleanConverter booleanConverter = new BooleanConverter();
    Boolean waar;

    @Test
    void convertToDatabaseColumnShouldReturnStringTRUEWhenBooleanTrueIsGiven() {
        //Given
        String returnString;
        Boolean waar = true;
        //When
        returnString = booleanConverter.convertToDatabaseColumn(waar);
        //Then
        assertThat(returnString, is("TRUE"));
    }

    @Test
    void convertToDatabaseColumnShouldReturnEmptyStringWhenNullIsInserted() {
        //Given
        String returnString;
        //When
        returnString = booleanConverter.convertToDatabaseColumn(waar);
        //Then
        assertThat(returnString, is(""));
    }

    @Test
    void convertToEntityAttributeShouldReturnTruewhenStringTrueIsGiven() {
        //Given
        String trueString = "TRUE";
        Boolean returnBoolean;
        //When
        returnBoolean = booleanConverter.convertToEntityAttribute(trueString);
        //then
        assertThat(returnBoolean, is(true));
    }

    @Test
    void convertToEntityAttributeShouldReturnFalsewhenStringFalseIsGiven() {
        //Given
        String trueString = "FALSE";
        Boolean returnBoolean;
        //When
        returnBoolean = booleanConverter.convertToEntityAttribute(trueString);
        //then
        assertThat(returnBoolean, is(false));
    }

    @Test
    void convertToEntityAttributeShouldReturnFalseWhenGivenWrongString() {
        //Given
        String trueString = "NIETWAAR";
        Boolean returnBoolean;
        //When
        returnBoolean = booleanConverter.convertToEntityAttribute(trueString);
        //then
        assertThat(returnBoolean, is(false));
    }

}