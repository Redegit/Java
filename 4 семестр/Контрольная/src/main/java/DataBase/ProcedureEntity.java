package DataBase;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "procedure", schema = "main", catalog = "")
public class ProcedureEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private String dt;
    @Basic
    @Column(name = "cabinet")
    private int cabinet;
    @Basic
    @Column(name = "patient_id")
    private Integer patientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getCabinet() {
        return cabinet;
    }

    public void setCabinet(int cabinet) {
        this.cabinet = cabinet;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcedureEntity that = (ProcedureEntity) o;

        if (id != that.id) return false;
        if (cabinet != that.cabinet) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (dt != null ? !dt.equals(that.dt) : that.dt != null) return false;
        if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (dt != null ? dt.hashCode() : 0);
        result = 31 * result + cabinet;
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        return result;
    }
    public List<Object> getAllProp() {
        return Arrays.asList(id, type, dt, cabinet, patientId);
    }

    @Override
    public String toString() {
        return "ProcedureEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dt='" + dt + '\'' +
                ", cabinet=" + cabinet +
                ", patientId=" + patientId +
                '}';
    }
}
