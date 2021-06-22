package az.code.myauto.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ImageDTO {
    private Long id;
    private String name;
}
