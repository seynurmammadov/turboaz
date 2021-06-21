package az.code.myauto.models.dtos;

import az.code.myauto.models.Listing;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
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

    public ListingListDTO(Listing listing) {
        this.id = listing.getId();
        this.makeName = listing.getAuto().getMake().getName();
        this.modelName = listing.getAuto().getModel().getName();
        if(listing.getImages()!=null)
        this.thumbnailUrl = listing.getImages().get(0).getUrl();
        this.cityName = listing.getCity().getName();
        this.price = listing.getAuto().getPrice();
        this.mileage = listing.getAuto().getMileage();
        this.year = listing.getAuto().getYear();
        this.updatedAt = listing.getUpdatedAt();
    }
}
