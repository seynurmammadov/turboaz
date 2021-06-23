package az.code.myauto.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private int statusCode;
    private String status;
    private String username;
    private String phone;
}


