package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person1 = new Person("Dima", 19);

        try {
            Person person2 = new Person("", 19);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            Person person3 = new Person("Dima", -1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}