public class StoneHouseBuilder implements HouseBuilder {

    private int floorsNumber;
    private String wallType;
    private String roofType;
    private double area;

    public StoneHouseBuilder() {
        super();
    }

    @Override
    public HouseBuilder setFloorsNumber() {
        this.floorsNumber = 9;
        return this;
    }

    @Override
    public HouseBuilder setWallType() {
        this.wallType = "Панельные стены";
        return this;
    }

    @Override
    public HouseBuilder setRoofType() {
        this.roofType = "Бетонная крыша";
        return this;
    }

    @Override
    public HouseBuilder setArea() {
        this.area = 200;
        return this;
    }

    public House build() {
        return new House(floorsNumber, wallType, roofType, area);
    }
}
