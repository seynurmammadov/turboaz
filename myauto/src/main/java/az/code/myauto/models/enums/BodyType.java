package az.code.myauto.models.enums;

public enum BodyType {
    SEDAN("SEDAN"),
    SUV("SUV"),
    HATCHBACK("HATCHBACK"),
    PICKUP("PICKUP"),
    CABRIOLET("CABRIOLET"),
    COUPE("COUPE");
    private final String name;
    BodyType(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
