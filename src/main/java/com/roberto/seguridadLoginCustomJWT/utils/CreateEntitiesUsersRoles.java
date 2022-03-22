package com.roberto.seguridadLoginCustomJWT.utils;

import com.roberto.seguridadLoginCustomJWT.enums.RoleType;
import com.roberto.seguridadLoginCustomJWT.models.Role;
import com.roberto.seguridadLoginCustomJWT.models.User;
import com.roberto.seguridadLoginCustomJWT.repository.UserRepository;
import com.roberto.seguridadLoginCustomJWT.services.RoleService;
import com.roberto.seguridadLoginCustomJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateEntitiesUsersRoles implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Create Test Roles
        /*Role roleAdmin = new Role(RoleType.ROLE_ADMIN);
        Role roleUser = new Role(RoleType.ROLE_USER);
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);*/

        // Create Test Users
        /*Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(roleAdmin);
        rolesAdmin.add(roleUser);
        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(roleUser);
        User userAdmin = new User("admin",
                passwordEncoder.encode("1234"),
                "admin@email.com", "admin");
        userAdmin.setRoles(rolesAdmin);
        User user = new User("roberto",
                passwordEncoder.encode("1234"),
                "roberto@email.com", "roberto");
        user.setRoles(rolesUser);
        userService.saveUser(userAdmin);
        userService.saveUser(user);*/

    }
}
