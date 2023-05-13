package org.example.Validator;

public class NotEmptyValidator implements FieldValidator<String> {
    public boolean isValid(String value) {
        return value != null && !value.isEmpty();
    }
}
