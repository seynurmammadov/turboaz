package az.code.myauto.models.dtos;

import az.code.myauto.models.Thumbnail;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ThumbnailDTO {
    Long id;
    private String thumbnail;

    public ThumbnailDTO(Thumbnail thumbnail) {
        this.id = thumbnail.getId();
        this.thumbnail = thumbnail.getUrl();
    }
}
