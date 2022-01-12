package com.egorbahar.service;

import com.egorbahar.entity.User;

public interface UserService {
    User save(User user);
    User findByUserName(String userName);
    User findByUserNameAndPassword(String userName, String password);
}
