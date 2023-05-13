package OOP_employee;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Employee[] emps = new Employee[5];
        emps[0] = new Employee("a", new Date("8/20/2020"), 1);
        emps[1] = new Employee("b", new Date("10/16/2019"), 2);
        emps[2] = new Employee("c", new Date("8/20/2017"), 2);
        emps[3] = new Employee("d", new Date("1/10/2000"), 3);
        emps[4] = new Employee("e", new Date("1/10/2000"), 5);

        for (var emp: emps)
            System.out.println(emp.toString());

        emps[0].setName("1");
        System.out.println("Edited:\n" + emps[0].toString());
    }
}
