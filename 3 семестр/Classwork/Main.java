package Classwork;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    System.out.println("What your name?");
    Scanner scanner = new Scanner(System.in);
    String firstname = scanner.nextLine();
    System.out.println("Hello, " + firstname);
    System.out.println("How old are you?");
    int age = Integer.parseInt(scanner.nextLine());
    System.out.println("What is your salary?");
    double salary=Double.parseDouble(scanner.nextLine());
    System.out.println("What your last name?");
    String lastname = scanner.nextLine();
    System.out.println(lastname);
    System.out.printf("Name: %s\nAge: %d\nSalary: %.2f\nLastname: %s",firstname, age, salary, lastname);

    }
}
