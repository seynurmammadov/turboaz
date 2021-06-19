package az.code.myauto.models.dtos;

import az.code.myauto.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ListingGetDTO {
    private Long id;
    private UserDto user;
    private MakeDTO make;
    private ModelDTO model;
    private int year;
    private int price;
    private int mileage;
    private String fuelType;
    private String bodyType;
    private String color;
    private CityDTO city;
    private String gearBox;
    private boolean autoPay;
    private Boolean creditOption;
    private Boolean barterOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private String description;
    private String type;
    private List<String> thumbnailUrl;
    private List<CarSpecDTO> carSpecs;
    private LocalDateTime updatedAt;
    private boolean isActive;

    public ListingGetDTO(Listing data){
        this.id = data.getId();
        this.user=new UserDto(data.getUser());
        this.make=new MakeDTO(data.getAuto().getMake());
        this.model= new ModelDTO(data.getAuto().getModel());
        this.year= data.getAuto().getYear();
        this.price=data.getAuto().getPrice();
        this.mileage=data.getAuto().getMileage();
        this.fuelType=data.getAuto().getFueltype().name();
        this.bodyType=data.getAuto().getBodyType().name();
        this.color=data.getAuto().getBodyType().name();
        this.city=new CityDTO(data.getCity());
        this.gearBox=data.getAuto().getGearBox().name();
        this.autoPay=data.isAuto_pay();
        this.creditOption=data.getCreditOption();
        this.barterOption=data.getBarterOption();
        this.leaseOption=data.getLeaseOption();
        this.cashOption=data.getCashOption();
        this.description=data.getDescription();
        this.type=data.getType().name();
        this.thumbnailUrl= data.getThumbnails().stream().map(Thumbnail::getUrl).collect(Collectors.toList());
        this.carSpecs= data.getAuto().getEquipments().stream().map(CarSpecDTO::new).collect(Collectors.toList());
        this.updatedAt=data.getUpdatedAt();
        this.isActive=data.isActive();
    }

}