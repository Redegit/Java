package homework;

import java.time.LocalTime;
import java.util.Arrays;

/*
 . Airline: Пункт назначения, Номер рейса, Тип самолета, Время вылета, Дни
        недели.
        Создать массив объектов. Вывести:
        a) список рейсов для заданного пункта назначения;
        b) список рейсов для заданного дня недели;
        c) список рейсов для заданного дня недели, время вылета для которых
        больше заданного.
*/


public class HW5_Presnukhin_PI21_2_Var24 {

    public static void main(String[] args) {
        Airline[] flights = new Airline[5];
        flights[0] = new Airline("Moscow", "21575", "Airbus A330", "21:05:00", new String[]{"WEN", "TUE", "MON", "FRI"});
        flights[1] = new Airline("Moscow", "11252", "Boeing-737", "15:25:00", new String[]{"THI", "SUN", "SAT"});
        flights[2] = new Airline("St Petersburg", "78545", "Airbus A330", "00:01:00", new String[]{"WEN", "TUE", "MON", "FRI"});
        flights[3] = new Airline("St Petersburg", "99856", "Boeing-737", "16:15:00", new String[]{"THI", "SUN", "SAT"});
        flights[4] = new Airline("Minsk", "25365", "Boeing-767", "19:55:00", new String[]{"MON", "FRI"});

        System.out.println("-------a-------");
        for (var f: flights)
            if (f.getDest().equals("Moscow"))
                System.out.println(f);

        System.out.println("-------b-------");
        for (var f: flights)
            if (Arrays.stream(f.getWeek_days()).anyMatch("SUN"::equals))
                System.out.println(f);

        System.out.println("-------c-------");
        LocalTime time = LocalTime.parse("15:30:00");
        for (var f: flights)
            if (Arrays.stream(f.getWeek_days()).anyMatch("SAT"::equals) && f.getStart_time().isAfter(time))
                System.out.println(f);

    }
}


class Airline {
    private String destination;
    public String getDest() {return destination;}
    private String flight_n;
    public String getFlight_n() {return flight_n;}
    private String plane_type;
    public String getPlane_type() {return plane_type;}
    private String[] week_days;
    public String[] getWeek_days() {return week_days;}
    private LocalTime start_time;
    public LocalTime getStart_time() {return start_time;}

    public Airline(String destination, String flight_n, String plane_type, String start_time, String[] week_days) {
        this.destination = destination;
        this.flight_n = flight_n;
        this.plane_type = plane_type;
        this.week_days = week_days;
        this.start_time = LocalTime.parse(start_time);
    }

    @Override
    public String toString() {
        return "To: " + destination + ", no: " + flight_n + ", plane: " + plane_type + ", start: " + start_time + ", days: " + Arrays.toString(week_days);
    }

    public static void main(String[] args) {}
}



