package az.code.myauto.models.enums;

public enum BodyType {
    SEDAN("Sedan"),
    SUV("SUV"),
    HATCHBACK("Hatchback"),
    PICKUP("Pickup"),
    CABRIOLET("Cabriolet"),
    COUPE("Coupe");
    private final String name;
    BodyType(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
