package az.code.myauto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
}
