package az.code.myauto.models.dtos;

import az.code.myauto.models.Image;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ImageDTO {
    Long id;
    private String thumbnail;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.thumbnail = image.getUrl();
    }
}
