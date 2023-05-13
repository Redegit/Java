package org.example.Validator;

public class PositiveNumberValidator implements FieldValidator<Number> {
    public boolean isValid(Number value) {
        return value != null && value.intValue() >= 0;
    }
}
