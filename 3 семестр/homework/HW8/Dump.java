package homework.HW8;

import java.io.Serializable;
import java.util.ArrayList;

public class Dump implements Serializable {
    ArrayList<TrainServicing> servicing_list;

    public Dump(ArrayList<TrainServicing> servicing_list) {
        this.servicing_list = servicing_list;
    }
}
