package org.patterns.Services;

public class ServiceUrgency {
    private String name;
    private float paymentScale;
    private int timeDays;

    public ServiceUrgency(String name, float paymentScale, int timeDays) {
        this.name = name;
        this.paymentScale = paymentScale;
        this.timeDays = timeDays;
    }

    public ServiceUrgency() {
    }


    public String getName() {
        return name;
    }

    public int getTimeDays() {
        return timeDays;
    }

    @Override
    public String toString() {
        return "ServiceUrgency{" +
                "name='" + name + '\'' +
                ", paymentScale=" + paymentScale +
                ", timeDays=" + timeDays +
                '}';
    }
}
