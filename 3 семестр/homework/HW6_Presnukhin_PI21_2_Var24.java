package d;

import java.util.ArrayList;

public class HW6_Presnukhin_PI21_2_Var24 {

    public static void main(String[] args) {
        var depot1 = new TecDepot("Лихоборы", "Лихоборы", "Москва", 10);
        var depot2 = new TecDepot("Орёл", "Орловская область", "Орёл", 11);
        var insp1 = new Inspection(depot1,"ТО-1", 2022, 10, 152, "Успешно");
        var insp1_1 = new Inspection(depot1,"ТО-2", 2023, 1, 95, "Успешно");
        var rep2 = new CurrentRepair(depot2, "ТР-1", 1225, "Неполадки с тормозной системой", "Тут какое-то описание", 999999);
        var ov2 = new Overhaul(depot2, 2021, 5, 105, 1000999);
        DepotList.show(); // выводит ремонты во всех депо
        System.out.println("\nВывод информации только о конкретном депо:\n");
        depot1.print_works(); // выводит работы, проводимые в конкретном депо
    }
}

class DepotList {
    static ArrayList<TecDepot> depot_list = new ArrayList<>();

    public static void show() {
        for (TecDepot depot: depot_list) {
            depot.print_works();
            System.out.println("----------------------------");
        }
    }

    public static void add(TecDepot depot) {
        DepotList.depot_list.add(depot);
    }
}

class TecDepot extends DepotList {
    String name;
    String address;
    String city;
    int n_employees;
    ArrayList<Inspection> inspection_list = new ArrayList<>();
    ArrayList<CurrentRepair> current_repair_list = new ArrayList<>();
    ArrayList<Overhaul> overhaul_list = new ArrayList<>();


    public TecDepot(String name, String address, String city, int n_employees) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.n_employees = n_employees;
        DepotList.add(this);
    }

    TecDepot() {
    }

    public void print_works() {
        System.out.println("Депо >> " + this);
        System.out.println(" << Планово-предупредительные осмотры >>");
        for (var inspection: this.inspection_list)
            System.out.println(inspection);
        System.out.println(" << Текущие ремонты >>");
        for (var repair: this.current_repair_list)
            System.out.println(repair);
        System.out.println(" << Капитальные ремонты >>");
        for (var overhaul: this.overhaul_list)
            System.out.println(overhaul);
    }
    @Override
    public String toString() {
        return "Название: " + name + ", адрес: " + city + ", " + address + ", к-во сотрудников: " + n_employees;
    }
}

class Inspection extends TecDepot {
    TecDepot depot;
    String rep_type;
    int year;
    int month;
    int car_n;
    String result;

    public Inspection(TecDepot depot, String rep_type, int year, int month, int car_n, String result) {
        this.depot = depot;
        this.rep_type = rep_type;
        this.year = year;
        this.month = month;
        this.car_n = car_n;
        this.result = result;
        depot.inspection_list.add(this);
    }

    @Override
    public String toString() {
        return  "Тип ремонта: '" + rep_type +
                "', год проведения: " + year +
                ", месяц: " + month +
                ", номер вагона: " + car_n +
                ", результат: '" + result + "'";
    }
}

class CurrentRepair extends TecDepot {
    TecDepot depot;
    String rep_type;
    int loc_n;
    String malfunctions;
    String work_desc;
    int cost;

    public CurrentRepair(TecDepot depot, String rep_type, int loc_n, String malfunctions, String work_desc, int cost) {
        this.depot = depot;
        this.rep_type = rep_type;
        this.loc_n = loc_n;
        this.malfunctions = malfunctions;
        this.work_desc = work_desc;
        this.cost = cost;
        depot.current_repair_list.add(this);
    }

    @Override
    public String toString() {
        return  "Тип ремонта: '" + rep_type +
                "', номер локомотива: " + loc_n +
                ", неисправности: '" + malfunctions +
                "', описание: '" + work_desc +
                "', стоимость: " + cost;
    };
}


class Overhaul extends TecDepot {
    TecDepot depot;
    int year;
    int month;
    int car_n;
    int cost;


    public Overhaul(TecDepot depot, int year, int month, int car_n, int cost) {
        this.depot = depot;
        this.year = year;
        this.month = month;
        this.car_n = car_n;
        this.cost = cost;
        depot.overhaul_list.add(this);

    }

    @Override
    public String toString() {
        return  "Год проведения: " + year +
                ", месяц: " + month +
                ", номер вагона: " + car_n +
                ", стоимость: " + cost;
    }
}