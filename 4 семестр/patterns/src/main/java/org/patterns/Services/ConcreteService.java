package org.patterns.Services;

public class ConcreteService implements Service {
    private final String label;
    private final double price;

    private final ServiceUrgency urgency;

    public ConcreteService(String label, double price, ServiceUrgency urgency) {
        this.label = label;
        this.price = price;
        this.urgency = urgency;
    }

    public double getPrice() {
        return this.price;
    }

    public String getLabel() {
        return this.label;
    }

    public ServiceUrgency getUrgency() {
        return urgency;
    }

    @Override
    public String toString() {
        return "ConcreteService{" +
                "label='" + label + '\'' +
                ", price=" + price +
                ", urgency=" + urgency +
                '}';
    }
}