package org.example;

import org.example.Validator.NotEmptyValidator;
import org.example.Validator.PositiveNumberValidator;
import org.example.Validator.ValidateField;
import org.example.Validator.Validator;

public class Person {
    @ValidateField(validator = NotEmptyValidator.class)
    private String name;

    @ValidateField(validator = PositiveNumberValidator.class)
    private int age;

    public Person(String name, int age) throws Exception {
        this.name = name;
        this.age = age;
        Validator.validate(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
