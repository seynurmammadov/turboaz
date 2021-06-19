package az.code.myauto.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ListingListDTO {
    Long id;
    String makeName;
    String modelName;
    String thumbnailUrl;
    String cityName;
    Integer price;
    Integer mileage;
    Integer year;
    LocalDateTime updatedAt;
}
