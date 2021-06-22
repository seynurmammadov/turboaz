package az.code.myauto.models.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class ListingGetDTO {

    private Long id;
    private UserDTO user;
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
    private List<ImageDTO> images;
    private List<CarSpecDTO> equipments;
    private LocalDateTime updatedAt;
    private boolean isActive;
}