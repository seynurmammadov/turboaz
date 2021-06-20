package az.code.myauto.models.dtos;

import az.code.myauto.models.Equipment;
import az.code.myauto.models.Subscription;
import az.code.myauto.models.enums.BodyType;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.enums.FuelType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class SubscriptionDto {
    private String name;

    private long subId;
    private long makeId;
    private long modelId;
    private long cityId;

    private String fuelType;
    private String bodyType;

    private String color;
    private List<Long> specs;

    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;

    private Boolean loanOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private Boolean barterOption;
    private LocalDateTime createdAt;

    public SubscriptionDto(Subscription data) {
        this.minYear=data.getMinYear();
        this.maxYear=data.getMaxYear();
        this.minPrice=data.getMinPrice();
        this.maxPrice=data.getMaxPrice();
        this.minMileage=data.getMinMileage();
        this.maxMileage=data.getMaxMileage();

        this.loanOption=data.getLoanOption();
        this.leaseOption=data.getLeaseOption();
        this.cashOption=data.getCashOption();
        this.barterOption=data.getBarterOption();

        this.name=data.getName();
        this.makeId=data.getMakeId();
        this.modelId=data.getModelId();
        this.cityId=data.getCityId();
        this.subId=data.getId();

        this.fuelType= data.getFuelType().name();
        this.bodyType= data.getBodyType().name();
        this.color= data.getColor().name();/*
        data.getEquipments().forEach(e -> specs.add(e.getId()));*/
        this.createdAt=data.getCreatedAt();
    }


}
