package pdp.uz.appnewssites2.companent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pdp.uz.appnewssites2.entity.Role;
import pdp.uz.appnewssites2.entity.User;
import pdp.uz.appnewssites2.entity.enums.Permission;
import pdp.uz.appnewssites2.repository.RoleRepository;
import pdp.uz.appnewssites2.repository.UserRepository;
import pdp.uz.appnewssites2.utils.AppConstants;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){
            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),
                    "Sistema egasi"

            ));

            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(Permission.ADD_COMMENT, Permission.EDIT_COMMENT, Permission.DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchi"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }
}
