package az.code.myauto.models.enums;

public enum ListingType {
    DEFAULT(0),
    STANDARD(5),
    VIP(15);
    private final double amount;
    ListingType(double amount) {
        this.amount=amount;
    }
    public double getAmount(){
        return this.amount;
    }
}
