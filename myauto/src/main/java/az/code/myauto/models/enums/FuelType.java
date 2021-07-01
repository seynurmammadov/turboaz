package az.code.myauto.models.enums;

public enum FuelType {
    PETROL("PETROL"),
    DIESEL("DIESEL"),
    ELECTRIC("ELECTRIC");
    private final String name;
    FuelType(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
