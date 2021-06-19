package az.code.myauto.models.dtos;

import az.code.myauto.models.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class UserDto {
    private String fullName;
    private String username;
    private String phone;
    public  UserDto(User data){
        this.username = data.getUsername();
        this.fullName = data.getFullname();
        this.phone = data.getPhonenumber();
    }
}
