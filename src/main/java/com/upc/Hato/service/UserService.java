package com.upc.Hato.service;

import com.upc.Hato.model.User;

public interface UserService {
    User findByUsername(String username);
    User createUser(User user);
}