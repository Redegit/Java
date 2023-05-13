package org.example.Validator;

public interface FieldValidator<T> {
    boolean isValid(T value);
}

