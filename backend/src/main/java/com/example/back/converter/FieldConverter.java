package com.example.back.converter;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.back.validator.ValidatorResult;
import com.example.back.entity.Color;
import com.example.back.entity.Country;

public class FieldConverter {
    public static String stringConvert(String data, String fieldName, ValidatorResult validatorResult){
        if (data == null || data.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
        }
        return data;
    }

    public static LocalDateTime localDateTimeConvert(String date, String pattern){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate ld = LocalDate.parse(date, formatter);
            return LocalDateTime.of(ld, LocalTime.of(0,0));
        } catch (Exception e){
            return null;
        }
    }

    public static LocalDateTime localFilterDateTimeConvert(String date, String fieldName, String pattern, ValidatorResult validatorResult){
        if (date == null || date.isEmpty()){
            return null;
        }

        return getLocalDateTime(date, fieldName, pattern, validatorResult);
    }

    private static LocalDateTime getLocalDateTime(String date, String fieldName, String pattern, ValidatorResult validatorResult) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate ld = LocalDate.parse(date, formatter);
            return LocalDateTime.of(ld, LocalTime.of(0,0));
        } catch (Exception e){
            validatorResult.addMessage(String.format("Couldn't convert %s: %s to decimal number", fieldName, date));
            return null;
        }
    }

    public static LocalDateTime localDateTimeConvert(String date, String fieldName, String pattern, ValidatorResult validatorResult){
        if (date == null || date.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }

        return getLocalDateTime(date, fieldName, pattern, validatorResult);
    }

    private static Float getFloat(String number, String fieldName, ValidatorResult validatorResult) {
        try {
            return Float.parseFloat(number.replace(",", "."));
        } catch (Exception e) {
            validatorResult.addMessage(String.format("Couldn't convert %s: %s to decimal number", fieldName, number));
            return null;
        }
    }

    public static Float floatFilterConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            return null;
        }

        return getFloat(number, fieldName, validatorResult);
    }


    public static Float floatConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }
        try {
            Float.parseFloat(number);
            return getFloat(number, fieldName, validatorResult);
        }
        catch (Exception ex) {
            validatorResult.addMessage(fieldName + " is not a float");
            return null;
        }
    }

    public static Float floatConvertPositive(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }
        try {
            Float.parseFloat(number);
            Float res = getFloat(number, fieldName, validatorResult);
            if (res <= 0) {
                validatorResult.addMessage(fieldName + " must be greater then 0");
                return null;
            }
            return res;
        }
        catch (Exception ex) {
            validatorResult.addMessage(fieldName + " is not a float");
            return null;
        }
    }

    private static Long getLong(String number, String fieldName, ValidatorResult validatorResult) {
        try {
            return Long.parseLong(number);
        } catch (Exception e) {
            validatorResult.addMessage(String.format("Couldn't convert %s: %s to decimal number", fieldName, number));
            return null;
        }
    }

    public static Long longFilterConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            return null;
        }

        return getLong(number, fieldName, validatorResult);
    }

    public static Long longConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }
        try {
            Long.parseLong(number);
            return getLong(number, fieldName, validatorResult);
        }
        catch (Exception ex) {
            validatorResult.addMessage(fieldName + " is not a long");
            return null;
        }
    }

    private static Color getColor(String color, String fieldName, ValidatorResult validatorResult) {
        try {
            return Color.valueOf(color);
        } catch (Exception e) {
            validatorResult.addMessage(fieldName + " is not GREEN, WHITE, BROWN");
            return null;
        }
    }

    public static Color colorFilterConvert(String color, String fieldName, ValidatorResult validatorResult){
        if (color == null || color.isEmpty()){
            return null;
        }

        return getColor(color, fieldName, validatorResult);
    }

    public static Color colorConvert(String color, String fieldName, ValidatorResult validatorResult){
        if (color == null || color.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }

        return getColor(color, fieldName, validatorResult);
    }

    private static Country getCountry(String country, String fieldName, ValidatorResult validatorResult) {
        try {
            return Country.valueOf(country);
        } catch (Exception e) {
            validatorResult.addMessage(fieldName + " is not VATICAN, THAILAND, JAPAN");
            return null;
        }
    }

    public static Country countryFilterConvert(String country, String fieldName, ValidatorResult validatorResult){
        if (country == null || country.isEmpty()){
            return null;
        }

        return getCountry(country, fieldName, validatorResult);
    }

    public static Country countryConvert(String country, String fieldName, ValidatorResult validatorResult){
        if (country == null || country.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }

        return getCountry(country, fieldName, validatorResult);
    }

    private static Integer getInteger(String number, String fieldName, ValidatorResult validatorResult) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            validatorResult.addMessage(String.format("Couldn't convert %s: %s to decimal number", fieldName, number));
            return null;
        }
    }

    public static Integer intFilterConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            return null;
        }

        return getInteger(number, fieldName, validatorResult);
    }

    public static Integer intConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }
        try {
            Integer.parseInt(number);
            return getInteger(number, fieldName, validatorResult);
        }
        catch (Exception ex) {
            validatorResult.addMessage(fieldName + " is not a integer");
            return null;
        }
    }

    public static Integer intConvert(String number, int defaultInteger){
        try {
            return Integer.parseInt(number);
        } catch (Exception e){
            return defaultInteger;
        }
    }

    private static Double getDouble(String number, String fieldName, ValidatorResult validatorResult) {
        try {
            return Double.parseDouble(number.replace(",", "."));
        } catch (Exception e) {
            validatorResult.addMessage(String.format("Couldn't convert %s: %s to decimal number", fieldName, number));
            return null;
        }
    }

    public static Double doubleFilterConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            return null;
        }

        return getDouble(number, fieldName, validatorResult);
    }


    public static Double doubleConvert(String number, String fieldName, ValidatorResult validatorResult){
        if (number == null || number.isEmpty()){
            validatorResult.addMessage(fieldName + " is not specified");
            return null;
        }

        return getDouble(number, fieldName, validatorResult);
    }

    public static String sortFieldFilterConvert(String sortField, List<String> fields, ValidatorResult validatorResult){

        if (sortField == null || sortField.isEmpty()){
            return null;
        }

        if (fields.contains(sortField)){
            return sortField;
        } else {
            validatorResult.addMessage("Unknown sortField: " + sortField);
            return null;
        }
    }

    public static String addPrefixFieldConvert(String prefix, String field){
        return prefix + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public static String stringLikeConvert(String string){
        return string + "%";
    }

    public static String removePrefixFieldConvert(String field, String prefix){
        return field.replaceAll(prefix, "").toLowerCase();
    }
}
