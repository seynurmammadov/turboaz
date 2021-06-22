package az.code.myauto.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserDTO {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
}
