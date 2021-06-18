package az.code.myauto.models.dtos;

import java.time.LocalDateTime;
import java.util.List;

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
    private boolean creditOption;
    private boolean barterOption;
    private boolean leaseOption;
    private boolean cashOption;
    private String description;
    private String type;
    private String thumbnailUrl;
    private List<CarSpecDTO> carSpecs;
    private LocalDateTime updatedAt;
    private boolean isActive;
}