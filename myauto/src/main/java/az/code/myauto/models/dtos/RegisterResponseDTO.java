package az.code.myauto.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class RegisterResponseDTO {
    String message; // because of the email verification message
}
