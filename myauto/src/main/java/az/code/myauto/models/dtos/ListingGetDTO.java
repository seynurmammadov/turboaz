package az.code.myauto.models.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ListingGetDTO { // detail
    Long id;
    MakeDTO make;
    ModelDTO model;
    int year;
    int price;
    int mileage;
    String fuelType;
    String bodyType;
    String color;
    CityDTO city;
    String gearBox;
    boolean auto_pay;
    boolean creditOption;
    boolean barterOption;
    boolean leaseOption;
    boolean cashOption;
    String description;
    String type;
    String thumbnailUrl;
    List<CarSpecDTO> carSpecs;
    private LocalDateTime updatedAt;
    private boolean isActive;
}
