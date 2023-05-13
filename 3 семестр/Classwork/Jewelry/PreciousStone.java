package Classwork.Jewelry;

public class PreciousStone extends Stone {

    public PreciousStone(double weight, double price, double transparency) {
        super(weight, price, transparency);
    }

    public String toString() {
        return "Драгоценный камень весом " + weight + ", стоимостью " + price + " и прозрачностью " + transparency;
    }
}
