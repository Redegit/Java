package org.patterns.Services;

import org.patterns.Services.Visitor.Visitor;

public class ServiceTypeDecorator implements Service {

    private final Service service;
    private final String label;
    private final double price;

    private Double discount = 0.;

    private final ServiceUrgency urgency;

    public ServiceTypeDecorator(Service service, String label, double price, ServiceUrgency urgency) {
        this.service = service;
        this.label = label;
        this.price = price;
        this.urgency = urgency;
    }

    public double getPrice() {
        return this.price + service.getPrice();
    }

    public String getLabel() {
        return this.label + ", " + service.getLabel();
    }

    public ServiceUrgency getUrgency() {
        return urgency;
    }

    @Override
    public String toString() {
        return "ServiceTypeDecorator{" +
                "service=" + service +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", urgency=" + urgency +
                ", discount=" + discount +
                '}';
    }

    public void steps() {
        offerService();
        takeSample();
        waitForResult();
        ready();
    }


    public void offerService() {
        System.out.println("Услуга заказана!");
    }

    public void takeSample() {
        System.out.println("Взятие пробы...");
    }

    public void waitForResult() {
        System.out.println("Ожидание резульатата " + this.urgency.getTimeDays() + " дней");
    }

    public void ready() {
        System.out.println("Готово");
    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}

