import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

class TrainServicing implements Serializable {
    static ArrayList<TrainServicing> servicing_list = new ArrayList<>();

    private int id;
    private LocalDate date;
    private String service_type;
    private int train_n;
    private int cost;

    public TrainServicing(int id, LocalDate date, String service_type, int train_n, int cost) {
        this.date = date;
        this.service_type = service_type;
        this.train_n = train_n;
        this.cost = cost;
        this.id = id;
        servicing_list.add(this);
    }

    static void print() {
        for (TrainServicing servicing : servicing_list)
            System.out.println(servicing);
    }

    @Override
    public String toString() {
        return "ID: " + id + ". " + service_type + " ТО состава №" + train_n + ". Стоимость работ: " + cost + ". Дата: " + date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public int getTrain_n() {
        return train_n;
    }

    public void setTrain_n(int train_n) {
        this.train_n = train_n;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
