package com.example.service;

import com.example.model.User;


import java.util.List;
import java.util.Optional;

public interface    UserService {
    List<User> findAllUser();

    void save(User user);


}
