package az.code.myauto.models.dtos;

import az.code.myauto.models.Equipment;
import az.code.myauto.models.Subscription;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class SubscriptionListDTO {
    private long subId;
    private String name;
    private MakeDTO make;
    private ModelDTO model;
    private CityDTO city;

    private int minYear;
    private int maxYear;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;

    private String fuelType;
    private String bodyType;
    private String color;

    private Boolean loanOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private Boolean barterOption;

    private LocalDateTime creationDate;

    private List<CarSpecDTO> specs= new ArrayList<>();
    public SubscriptionListDTO(Subscription data) {
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
        this.make= new MakeDTO(data.getMake());
        this.model= new ModelDTO(data.getModel());
        this.city= new CityDTO(data.getCity());
        this.subId=data.getId();

        this.fuelType= data.getFuelType().name();
        this.bodyType= data.getBodyType().name();
        this.color= data.getColor().name();
        data.getEquipments().forEach(e -> specs.add(new CarSpecDTO(e.getName())));
        this.creationDate=data.getCreatedAt();
    }
}
