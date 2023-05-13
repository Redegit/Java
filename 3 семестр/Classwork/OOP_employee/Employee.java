package OOP_employee;

import java.util.Date;

public class Employee {

    private Date hireDate;
    private int level;
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name, Date hireDate, int level) {
        this.name = name;
        this.hireDate = hireDate;
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("%5s \t %10s \t %d",
            name, hireDate.getDate()+"."+(hireDate.getMonth()+1)+"."+(hireDate.getYear()+1900), level);
    }


    public static void main(String[] args) {

    }

}
