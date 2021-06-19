package az.code.myauto.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class RegisterDTO {
    String name;
    String surname;
    String email;
    String password;
}
