package az.code.myauto.models.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class SubscriptionDto {
    private long subId;
    private String name;
    private long makeId;
    private long modelId;
    private long cityId;
    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;
    private int fuelType;
    private int bodyType;
    private boolean loanOption;
    private boolean leaseOption;
    private boolean cashOption;
    private boolean barterOption;
    private String color;
    private List<Long> specs;
}
