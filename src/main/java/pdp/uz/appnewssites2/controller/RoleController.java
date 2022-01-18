package pdp.uz.appnewssites2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appnewssites2.aop.CheckPermission;
import pdp.uz.appnewssites2.payload.ApiResponse;
import pdp.uz.appnewssites2.payload.RoleDto;
import pdp.uz.appnewssites2.service.RoleService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

//    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @CheckPermission(value = "ADD_ROLE")
    @PostMapping
    public HttpEntity<?> register(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @CheckPermission(value = "EDIT_ROLE")
    @PutMapping("{id}")
    public HttpEntity<?> editRole(@Valid @RequestBody RoleDto roleDto,@PathVariable Long id){
        ApiResponse apiResponse = roleService.editRole(roleDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
