import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class DB {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:DB\\TrainServicing");

//        System.out.println("База Подключена!");
    }

    public static void Read() throws ClassNotFoundException, SQLException {
        statmt = conn.createStatement();

        String x = null;
        int where_id = 0;
        while (true) {
            x = Main.input("id техобслуживания");
            if (Objects.equals(x, "")) {
                System.out.println("Возврат в меню...");
                return;
            }
        try {
            where_id = Integer.parseInt(x);
            break;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод");
            }
        }
        resSet = statmt.executeQuery("SELECT * FROM TrainServicing WHERE id = %s".formatted(where_id));

        int id = resSet.getInt("id");
        String date = resSet.getString("dt");
        String service_type = resSet.getString("service_type");
        int train_n = resSet.getInt("train_n");
        int cost = resSet.getInt("cost");
        new TrainServicing(id, LocalDate.parse(date), service_type, train_n, cost);

        System.out.println("Запись с id " + id + " загружена в коллекцию");
    }

    public static void ReadAll() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();

        resSet = statmt.executeQuery("SELECT * FROM TrainServicing");

        int i = 0;
        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String date = resSet.getString("dt");
            String service_type = resSet.getString("service_type");
            int train_n = resSet.getInt("train_n");
            int cost = resSet.getInt("cost");
            new TrainServicing(id, LocalDate.parse(date), service_type, train_n, cost);
            i += 1;
        }

        System.out.println("Данные загружены в коллекцию. Число объектов: " + i);
    }

    private static boolean Writer(TrainServicing servicing) throws SQLException
    {
        int id = servicing.getId();
        LocalDate dt = servicing.getDate();
        String service_type = servicing.getService_type();
        int train_n = servicing.getTrain_n();
        int cost = servicing.getCost();
        statmt = conn.createStatement();

        try {
            statmt.executeUpdate("INSERT INTO TrainServicing VALUES " +
                    "(%s, '%s', '%s', %s, %s); ".formatted(id, dt, service_type, train_n, cost));
            System.out.println("Запись с id " + id + " добавлена");

        } catch (SQLException e) {
            if (String.valueOf(e).contains("UNIQUE constraint failed")) {
                System.out.println("Объект с ID " + id + " уже существует");
            }
            else {
                System.out.println(e);
            }
            return false;
        }
        return true;
    }

    public static void WriteAll(ArrayList<TrainServicing> service_list) throws SQLException {
        int i = 0;

        for (TrainServicing servicing : service_list) {
            if (DB.Writer(servicing)) {
                i += 1;
            }
        }
        System.out.println("Загружено объектов: " + i);
    }

    public static void Close() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
//        resSet.close();

        System.out.println("Соединение с БД закрыто");
    }

}
