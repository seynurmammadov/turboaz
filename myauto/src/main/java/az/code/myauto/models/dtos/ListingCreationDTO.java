package az.code.myauto.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ListingCreationDTO {
    private Long makeId;
    private Long modelId;
    private int year;
    private int price;
    private int mileage;
    private String fuelType;
    private String bodyType;
    private String color;
    private Long cityId;
    private String gearBox;
    private boolean auto_pay;
    private Boolean creditOption;
    private Boolean barterOption;
    private Boolean leaseOption;
    private Boolean cashOption;
    private String description;
    private String type;
    private String thumbnailUrl;
    @JsonProperty("carSpecIds")
    private List<CarSpecDTO> equipments;
}
