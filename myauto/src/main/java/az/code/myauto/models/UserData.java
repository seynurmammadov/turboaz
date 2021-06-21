package az.code.myauto.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class UserData {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
}
