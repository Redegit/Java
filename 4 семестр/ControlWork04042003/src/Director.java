public class Director {

    private final HouseBuilder builder;

    public Director(HouseBuilder builder) {
        super();
        this.builder = builder;
        if (this.builder == null) {
            throw new IllegalArgumentException("Ошибка");
        }
    }

    public House buildHouse() {
        return builder.setFloorsNumber().setWallType().setRoofType().setArea().build();
    }
}
