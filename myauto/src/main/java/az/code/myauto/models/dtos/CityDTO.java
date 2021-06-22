package az.code.myauto.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class CityDTO {
    Long id;
    String name;
}
