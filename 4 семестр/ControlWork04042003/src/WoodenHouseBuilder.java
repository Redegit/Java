public class WoodenHouseBuilder implements HouseBuilder {

    private int floorsNumber;
    private String wallType;
    private String roofType;
    private double area;

    public WoodenHouseBuilder() {
        super();
    }

    @Override
    public HouseBuilder setFloorsNumber() {
        this.floorsNumber = 1;
        return this;
    }

    @Override
    public HouseBuilder setWallType() {
        this.wallType = "Деревянные стены";
        return this;
    }

    @Override
    public HouseBuilder setRoofType() {
        this.roofType = "Деревянная крыша";
        return this;
    }

    @Override
    public HouseBuilder setArea() {
        this.area = 40.5;
        return this;
    }

    public House build() {
        return new House(floorsNumber, wallType, roofType, area);
    }
}
