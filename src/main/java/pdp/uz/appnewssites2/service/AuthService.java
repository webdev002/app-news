package pdp.uz.appnewssites2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.uz.appnewssites2.entity.Role;
import pdp.uz.appnewssites2.entity.User;
import pdp.uz.appnewssites2.exception.ResourceNotException;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.LoginDto;
import pdp.uz.appnewssites2.payload.RegisterDto;
import pdp.uz.appnewssites2.repository.RoleRepository;
import pdp.uz.appnewssites2.repository.UserRepository;
import pdp.uz.appnewssites2.security.JwtProvider;
import pdp.uz.appnewssites2.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("Parollar mos emas! ",false);
        }
        boolean exists = userRepository.existsByUsername(registerDto.getUsername());
        if (exists){
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan ",false);
        }
      User  user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotException("role","name",AppConstants.USER)),
              true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yxatan o'tdingiz",true);


    }

    public UserDetails loadUserByUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException(username));
    }

    public ApiResponse loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user=(User)authentication.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
        return new ApiResponse("Token",true,token);
    }
}
