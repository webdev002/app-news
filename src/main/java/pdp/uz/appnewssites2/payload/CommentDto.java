package pdp.uz.appnewssites2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appnewssites2.entity.Post;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull(message = "Text bo'sh bo'lmasin")
    private String text;

    @NotNull(message = "postni idsini bermadingiz.")
    private Long post;
}
