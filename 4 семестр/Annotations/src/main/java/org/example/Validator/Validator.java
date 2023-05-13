package org.example.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Validator {
    public static void validate(Object obj) throws Exception {
        for (Field field : obj.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof ValidateField) {
                    ValidateField validateAnnot = (ValidateField) annotation;
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (!validateAnnot.validator().newInstance().isValid(value)) {
                        throw new IllegalArgumentException(
                                String.format("Объект %s: Невалидное значение '%s' для поля %s", obj, value, field.getName()));
                    }
                }
            }
        }
        System.out.println("Объект " + obj + " успешно создан");
    }
}
