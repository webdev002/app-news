package pdp.uz.appnewssites2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotNull(message = "title bosh bolmasin")
    private String title;

    @NotNull(message = "text bosh bolmasin")
    private String text;

    @NotNull(message = "url bosh bolmasin")
    private  String url;
}
