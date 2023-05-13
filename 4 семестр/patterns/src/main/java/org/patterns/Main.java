package org.patterns;

import org.patterns.Services.ConcreteService;
import org.patterns.Services.Types.BloodTest;
import org.patterns.Services.Types.CovidTest;
import org.patterns.Services.Types.DnaAnalysis;
import org.patterns.Services.Visitor.LargeFamily;
import org.patterns.Services.Visitor.Pensioner;

import static org.patterns.Services.ServiceFactory.getServiceUrgency;
import static org.patterns.Services.ServiceFactory.getServiceUrgencyByName;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------------Демонстрация использования декоратора и легковеса------------");
        var fast = getServiceUrgency("fast", 1.5f, 1);
        var common = getServiceUrgency("common", 1f, 3);
        var slow = getServiceUrgency("slow", 1f, 10);

        var baseTest = new ConcreteService("Полное обследование", 1000, fast);

        var bloodTest = new BloodTest(baseTest);
        var dnaTest = new DnaAnalysis(bloodTest);
        var allTestsInOne = new CovidTest(dnaTest);

//        System.out.println(allTestsInOne);
        System.out.println("Общая стоимость: " + allTestsInOne.getPrice());
        System.out.println("Услуги: " + allTestsInOne.getLabel());
        System.out.println("Срочность: " + allTestsInOne.getUrgency().getName());
        System.out.println("Условия: " + getServiceUrgencyByName(allTestsInOne.getUrgency().getName()));


        System.out.println("-------------Использование шаблонных методов------------");
        var bloodTest2 = new BloodTest(baseTest);
        bloodTest2.steps();

        System.out.println("-----------");
        var baseTest2 = new ConcreteService("", 0, slow);
        var dnaTest2 = new DnaAnalysis(baseTest2);dnaTest2.steps();

        System.out.println("-------------Использование посетителей------------");
        var largeFamily = new LargeFamily();
        bloodTest2.accept(largeFamily);
        System.out.println("Скидка для многодетной семьи на анализ крови: " + bloodTest2.getDiscount() * 100 + "%");

        System.out.println("-----------");
        var oldMan = new Pensioner();
        dnaTest2.accept(oldMan);
        System.out.println("Скидка для пенсионера на ДНК-тест: " + dnaTest2.getDiscount() * 100 + "%");
    }
}