package homework;

import java.util.Scanner;

// вводится сумма кредита, колво лет и процент
// вывести ежемесячный платёх и общую сумму платежа

public class Credit {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Введите сумму кредита: ");
        int sum=sc.nextInt();
        System.out.print("Введите срок кредита (лет): ");
        int time=sc.nextInt()*12; // количество месяцев
        System.out.print("Введите процентную ставку: ");
        double p=sc.nextInt()/100.0/12.0; // процентная ставка в месяц

        double month_payment=sum*(p+(p/(Math.pow((1+p), time)-1)));
        System.out.printf("Ежемесячный плтёж: %.2f\n", month_payment);
        System.out.printf("Общая сумма выплат: %.2f", month_payment*time);
    }
}
