public class House {

    private int floorsNumber;
    private String wallType;
    private String roofType;
    private double area;

    @Override
    public String toString() {
        return "House{" +
                "floorsNumber=" + floorsNumber +
                ", wallType='" + wallType + '\'' +
                ", roofType='" + roofType + '\'' +
                ", area=" + area +
                '}';
    }

    public House(int floorsNumber, String wallType, String roofType, double area) {
        this.floorsNumber = floorsNumber;
        this.wallType = wallType;
        this.roofType = roofType;
        this.area = area;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public void setFloorsNumber(int floorsNumber) {
        this.floorsNumber = floorsNumber;
    }

    public String getWallType() {
        return wallType;
    }

    public void setWallType(String wallType) {
        this.wallType = wallType;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
