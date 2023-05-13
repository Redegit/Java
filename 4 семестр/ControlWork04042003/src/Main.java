public class Main {

    public static void main(String[] args) {
        HouseBuilder builder = new WoodenHouseBuilder();
        Director director = new Director(builder);
        House house = director.buildHouse();
        System.out.println(house);

        HouseBuilder builder2 = new StoneHouseBuilder();
        Director director2 = new Director(builder2);
        House house2 = director2.buildHouse();
        System.out.println(house2);
    }
}