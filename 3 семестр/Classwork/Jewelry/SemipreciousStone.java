package Classwork.Jewelry;

public class SemipreciousStone extends Stone{

    public SemipreciousStone(double weight, double price, double transparency) {
        super(weight, price, transparency);
    }

    public String toString() {
        return "Полудрагоценныйы камень весом " + weight + ", стоимостью " + price + " и прозрачностью " + transparency;
    }
}
