package pdp.uz.appnewssites2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appnewssites2.entity.enums.Permission;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @NotBlank
    @NotNull(message = "name bosh bolmasin")
    private String name;

    private String description;

    @NotNull(message = "permissionList bosh bolmasin")
    @NotEmpty
    private List<Permission> permissionList;


}
