//Вариант 24).
//   Техобслуживание подвижного состава.
//   Организовать сохранение объектов из коллекции в текстовый файл и загрузку объектов из текстового файла в коллекцию.
//   Изменить пользовательское меню для добавления работы с внешними файлами.


import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        createSampleObjects();
        try {
            DB.Conn();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
//        createSampleObjects();
        mainMenu();
    }

    public static void createSampleObjects() {
        new TrainServicing(1, LocalDate.of(2022, Month.NOVEMBER, 4),
                "Плановое",
                21,
                1856);
        new TrainServicing(2, LocalDate.of(2020, Month.OCTOBER, 1),
                "Срочное",
                3,
                4865);
        new TrainServicing(3, LocalDate.of(2022, Month.OCTOBER, 5),
                "Плановое",
                545,
                0);
        new TrainServicing(4, LocalDate.of(2021, Month.SEPTEMBER, 30),
                "Плановое",
                7,
                2000);
    }

    public static void mainMenu() {
        int x = 0;
        String s = "";

        System.out.println("Добро пожаловать в консольное меню для работы с классом TrainServicing!");

        while (!"4".equals(s)) {
            System.out.println("\n------------ MAIN MENU ------------");
            System.out.println("1. Создать запись о ТО");
            System.out.println("2. Отсортировать и вывести список ТО");
            System.out.println("3. Сохранить/загрузить");
            System.out.println("4. Выход");
            System.out.print(">> ");
            s = scan.nextLine();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
                continue;
            }

            switch (x) {
                case 1 -> addServiceMenu();
                case 2 -> {
                    if (TrainServicing.servicing_list.size() > 0)
                        sortingMenu();
                    else System.out.println("Список пуст");
                }
                case 3 -> saveLoadMenu();
                case 4 -> {
                    try {
                        DB.Close();
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println(e);
                    }
                    System.out.println("Программа завершена");
                }
                default -> System.out.println("Некорректрый выбор");
            }
        }
    }

    public static void addServiceMenu() {
        int id;
        while (true) {
            try {
                id = Integer.parseInt(input("ID техобслуживания"));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введённое не является числом");
            }
        }
        LocalDate date;
        while (true) {
            try {
                date = LocalDate.parse(input("Дата в формате (YYYY-MM-DD)"));
                break;
            } catch (DateTimeException e) {
                System.out.println(e + "\nНеверный формат");
            }
        }

        String service_type = input("Тип ТО (Срочное/Плановое)");
        int train_n;
        while (true) {
            try {
                train_n = Integer.parseInt(input("Номер состава"));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введённое не является числом");
            }
        }

        int cost;
        while (true) {
            try {
                cost = Integer.parseInt(input("Стоимость работ"));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введённое не является числом");
            }
        }

        new TrainServicing(id, date, service_type, train_n, cost);
        System.out.println("Объект успешно создан!");
    }

    public static void sortingMenu() {
        int x = 0;
        String s = "";

        while (!"5".equals(s)) {
            System.out.println("\nВыберите параметр сортировки");
            System.out.println("1. Дата");
            System.out.println("2. Стоимость");
            System.out.println("3. Номер состава");
            System.out.println("4. ID");
            System.out.println("5. Назад");
            System.out.print(">> ");
            s = scan.nextLine();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
                continue;
            }

            Comparator<TrainServicing> comparator;
            switch (x) {
                case 1 -> comparator = Comparator.comparing(TrainServicing::getDate);
                case 2 -> comparator = Comparator.comparing(TrainServicing::getCost);
                case 3 -> comparator = Comparator.comparing(TrainServicing::getTrain_n);
                case 4 -> comparator = Comparator.comparing(TrainServicing::getId);
                case 5 -> {
                    System.out.println("Возврат в меню...");
                    return;
                }
                default -> {
                    System.out.println("Некорректный выбор");
                    continue;
                }
            }

            String order = sortingOrderMenu();

            switch (order) {
                case "ASCENDING" -> TrainServicing.servicing_list.sort(comparator);
                case "DESCENDING" -> TrainServicing.servicing_list.sort(comparator.reversed());
                default -> {
                    continue;
                }
            }
            TrainServicing.print();
            return;
        }
    }

    public static String sortingOrderMenu() {

        String s = "";
        int order = 0;

        while (!"3".equals(s)) {
            System.out.println("\nВыберите порядок сортировки");
            System.out.println("1. По убыванию");
            System.out.println("2. По возрастанию");
            System.out.println("3. Назад");
            System.out.print(">> ");
            s = scan.nextLine();

            try {
                order = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
            }

            switch (order) {
                case 1 -> {
                    return "DESCENDING";
                }
                case 2 -> {
                    return "ASCENDING";
                }
                case 3 -> System.out.println("Возврат в меню сортировки...\n");
                default -> System.out.println("Некорректный ввод\n");
            }
        }
        return "BACK";
    }

    public static void saveLoadMenu() {
        int x = 0;
        String s = "";

        while (!"5".equals(s)) {
            System.out.println("\nВыберите, что хотите сделать");
            System.out.println("1. Сохранить в файл");
            System.out.println("2. Загрузить из файла");
            System.out.println("3. Сохранить в БД");
            System.out.println("4. Загрузить из БД");
            System.out.println("5. Назад");
            System.out.print(">> ");
            s = scan.nextLine();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
                continue;
            }

            switch (x) {
                case 1 -> {
                    if (TrainServicing.servicing_list.size() > 0) {
                        try {
                            String filename = input("Введите название фалйа");
                            FileOutputStream outputStream = new FileOutputStream(filename);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                            Dump dump = new Dump(TrainServicing.servicing_list);

                            objectOutputStream.writeObject(dump);

                            objectOutputStream.close();

                            System.out.printf("Объектов сохранено %s\n", dump.servicing_list.size());
                        } catch (IOException e) {
                            System.out.println("Error: " + e);
                            continue;
                        }
                    } else {
                        System.out.println("Список пуст");
                        continue;
                    }

                }
                case 2 -> {
                    try {
                        String filename = input("Введите название фалйа");
                        FileInputStream fileInputStream = new FileInputStream(filename);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                        Dump loadedDump = (Dump) (objectInputStream.readObject());

                        TrainServicing.servicing_list.addAll(loadedDump.servicing_list);
                        System.out.printf("Объектов загружено: %s\n", loadedDump.servicing_list.size());
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error: " + e);
                        continue;
                    }
                }
                case 3 -> DBSaveMenu();
                case 4 -> DBReadMenu();
                case 5 -> {
                    System.out.println("Возврат в меню...");
                    return;
                }
                default -> {
                    System.out.println("Некорректный выбор");
                    continue;
                }
            }
        }
    }

    public static void DBSaveMenu() {
        int x = 0;
        String s = "";

        while (!"3".equals(s)) {
            System.out.println("\nВыберите, что хотите сделать");
            System.out.println("1. Сохранить записи по id");
            System.out.println("2. Сохранить всё");
            System.out.println("3. Назад");
            System.out.print(">> ");
            s = scan.nextLine();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
                continue;
            }

            switch (x) {
                case 1 -> {
                    String[] ids = input("Введите нужные id через пробел").split(" ");
                    if (ids.length > 0) {
                        ArrayList<TrainServicing> list_to_write = new ArrayList<>();
                        for (TrainServicing service : TrainServicing.servicing_list) {
                            if (Arrays.asList(ids).contains(String.valueOf(service.getId()))) {
                                list_to_write.add(service);
                            }
                        }
                        try {
                            DB.WriteAll(list_to_write);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } else System.out.println("Нет совпадений id");
                }

                case 2 -> {
                    if (TrainServicing.servicing_list.size() > 0) {
                        try {
                            DB.WriteAll(TrainServicing.servicing_list);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Список пуст");
                        continue;
                    }
                }
            }
        }
    }
    public static void DBReadMenu() {
        int x = 0;
        String s = "";

        while (!"3".equals(s)) {
            System.out.println("\nВыберите, что хотите сделать");
            System.out.println("1. Загрузить запись по id");
            System.out.println("2. Загрузить всё");
            System.out.println("3. Назад");
            System.out.print(">> ");
            s = scan.nextLine();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
                continue;
            }

            switch (x) {
                case 1 -> {
                    try {
                        DB.Read();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    try {
                        DB.ReadAll();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    System.out.println("Возврат...");
                    return;
                }
                default -> System.out.println("Некорректный выбор");
            }
        }
    }

    public static String input(String out_text) {
        System.out.print(out_text + " >> ");
        return scan.nextLine();
    }
}

