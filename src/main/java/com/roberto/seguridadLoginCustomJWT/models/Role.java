package com.roberto.seguridadLoginCustomJWT.models;

import com.roberto.seguridadLoginCustomJWT.enums.RoleType;
import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType rolename;

    public Role() {
    }

    public Role(RoleType rolename) {
        this.rolename = rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRolename() {
        return rolename;
    }

    public void setRolename(RoleType rolename) {
        this.rolename = rolename;
    }
}
