package az.code.myauto.models.dtos;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class ModelDTO {
    Long id;
    String name;
}
