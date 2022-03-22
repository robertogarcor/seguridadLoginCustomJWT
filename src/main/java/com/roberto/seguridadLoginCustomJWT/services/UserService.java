package com.roberto.seguridadLoginCustomJWT.services;

import com.roberto.seguridadLoginCustomJWT.models.User;

import java.util.Optional;

public interface UserService {
    public void saveUser(User user);
    public Optional<User> getByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
