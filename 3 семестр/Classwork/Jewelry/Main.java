package Classwork.Jewelry;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Stone[] stoneList = new Stone[5];
        stoneList[0] = new PreciousStone(15, 100000, 0.99);
        stoneList[1] = new PreciousStone(46, 1000000, 0.92);
        stoneList[2] = new SemipreciousStone(72, 19000, 0.41);
        stoneList[3] = new SemipreciousStone(100, 32000, 0.59);
        stoneList[4] = new PreciousStone(8, 37000, 0.98);

        double total_weight = 0, total_price = 0;

        // считаем суммарные вес и стоимость
        for (Stone stone : stoneList) {
            total_weight += stone.weight;
            total_price += stone.price;
        }

        System.out.println("Суммарная стоимость: " + total_price + "\nСуммарный вес: " + total_weight);

        // стортируем массив по цене и выводим
        System.out.println("\n--- Отсортированные по цене камни ---");
        Stone[] sorted_list = Main.sortByPrice(stoneList);
        for (Stone stone : sorted_list) {
            System.out.println(stone);
        }

        // задаем диапазон прозрачности и выводим камни, попадающие в него
        double min_transparency = 0.95, max_transparency = 1;
        System.out.println("\n--- Камни с проозрачностью в диапазоне от " + min_transparency + " до " + max_transparency + " ---");
        for (Stone stone : sorted_list) {
            if ((min_transparency <= stone.transparency) && (stone.transparency <= max_transparency))
                    System.out.println(stone);
        }

    }

    public static Stone[] sortByPrice(Stone[] arr) {
        int len = arr.length;
        Stone temp;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < (len - i); j++) {
                if (arr[j - 1].price > arr[j].price) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
