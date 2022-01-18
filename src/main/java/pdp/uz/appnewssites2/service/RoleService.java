package pdp.uz.appnewssites2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appnewssites2.entity.Role;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.RoleDto;
import pdp.uz.appnewssites2.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        boolean exists = roleRepository.existsByName(roleDto.getName());
        if (exists){
            return new ApiResponse("Bunday role bor",false);
        }
        Role role =new Role(
                roleDto.getName(),
                roleDto.getPermissionList(),
                roleDto.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("Saqlandi",true);

    }


    public ApiResponse editRole(RoleDto roleDto, Long id) {
        return new ApiResponse("ssss",true);
    }
}
