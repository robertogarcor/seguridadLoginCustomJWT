package com.roberto.seguridadLoginCustomJWT.services;

import com.roberto.seguridadLoginCustomJWT.enums.RoleType;
import com.roberto.seguridadLoginCustomJWT.models.Role;
import com.roberto.seguridadLoginCustomJWT.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> getByRolename(RoleType rolename) {
        System.out.println(roleRepository.findByRolename(rolename));
        return roleRepository.findByRolename(rolename);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }



}
