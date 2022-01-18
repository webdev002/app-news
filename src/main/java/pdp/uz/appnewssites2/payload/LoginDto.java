package pdp.uz.appnewssites2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "username bosh bolmasin")
    private String username;

    @NotNull(message = "password bosh bolmasin")
    private String password;


}
