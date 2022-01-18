package pdp.uz.appnewssites2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "fullname bosh bolmasin")
    private String fullName;

    @NotNull(message = "username bosh bolmasin")
    private String username;

    @NotNull(message = "password bosh bolmasin")
    private String password;

    @NotNull(message = "role bosh bolmasin")
    private Integer roleId;
}
