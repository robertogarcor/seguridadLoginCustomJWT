package com.roberto.seguridadLoginCustomJWT.services;

import com.roberto.seguridadLoginCustomJWT.enums.RoleType;
import com.roberto.seguridadLoginCustomJWT.models.Role;

import java.util.Optional;

public interface RoleService {
    public Optional<Role> getByRolename(RoleType rolename);
    public void saveRole(Role role);

}
