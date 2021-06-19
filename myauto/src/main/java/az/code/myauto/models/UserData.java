package az.code.myauto.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserData {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
}
