package com.mange.api.service;

import com.mange.api.entity.User;

import java.util.List;

public interface ManagerService {
    List<User> getAllUser(int type);

    int save(User user);

    User findByUsername(String username);

}
