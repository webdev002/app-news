package pdp.uz.appnewssites2.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pdp.uz.appnewssites2.entity.User;
import pdp.uz.appnewssites2.exception.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExample {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (user.getAuthorities().stream().filter())
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())){
                exist=true;
                break;
            }
        }
        if (!exist){
            throw  new ForbiddenException(checkPermission.value(),"Ruxsat yo'q.");
        }
    }
}
