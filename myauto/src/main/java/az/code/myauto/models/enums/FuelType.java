package az.code.myauto.models.enums;

public enum FuelType {
    PETROL("Petrol"),
    DIESEL("Diesel"),
    ELECTRIC("Electric");
    private final String name;
    FuelType(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
