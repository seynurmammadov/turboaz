package az.code.myauto.models.dtos;

import az.code.myauto.models.User;
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

    public UserDTO(User data) {
        this.username = data.getUsername();
        this.fullName = data.getFullname();
        this.phoneNumber = data.getPhonenumber();
        this.email = data.getEmail();
    }
}
