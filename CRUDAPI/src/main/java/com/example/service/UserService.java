package com.example.service;

import com.example.model.User;


import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllProduct();
    Optional<User> findById(Integer id);
    void save(User user);
    void remove(User user);

}
