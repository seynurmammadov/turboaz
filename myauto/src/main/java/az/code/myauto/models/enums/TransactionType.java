package az.code.myauto.models.enums;

public enum TransactionType {
    DEBIT("Increase"),
    CREDIT("Decrease");
    private final String operationName;
    TransactionType(String operationName) {
        this.operationName=operationName;
    }
    public String getOperationName(){
        return this.operationName;
    }
}
