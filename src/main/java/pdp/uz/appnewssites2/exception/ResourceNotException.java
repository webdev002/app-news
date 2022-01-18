package pdp.uz.appnewssites2.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotException extends RuntimeException{

    private final String  resourceName;//role

    private final String resourceField;//name

    private final Object object;//ADMIN,USER,1,399,23...

}
