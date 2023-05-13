package DataBase;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "TrainServicing", schema = "main")
public class TrainServicingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "dt", nullable = false)
    private String dt;
    @Column(name = "service_type", nullable = false, length = 25)
    private String serviceType;
    @Column(name = "train_n", nullable = false)
    private int trainN;
    @Column(name = "cost", nullable = false)
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainN() {
        return trainN;
    }

    public void setTrainN(int trainN) {
        this.trainN = trainN;
    }

    @Override
    public String toString() {
        return "TrainServicingEntity{" +
                "id=" + id +
                ", dt='" + dt + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", trainN=" + trainN +
                ", cost=" + cost +
                '}';
    }

    public List<Object> getAllProp() {
        return Arrays.asList(id, dt, serviceType, trainN, cost);
    }
}
