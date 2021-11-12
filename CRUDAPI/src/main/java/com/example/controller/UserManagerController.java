package com.example.controller;

import com.esotericsoftware.yamlbeans.YamlException;
import com.example.common.GsonCustom;

import com.example.model.User;
import com.example.model.UserLs;
import com.example.service.UserService;
import com.example.syncer.RedisSyncer;
import com.example.valida.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserManagerController {
    private static final Logger LOG = LogManager.getLogger(UserManagerController.class);
    public final static String REIDS_ID = "redis_id";

    private UserService userService;
    @Autowired
    public UserManagerController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUser() {
        UserLs userList = null;
        try {
            LOG.info("Get all user");
            List<User> users = userService.findAllUser();
            userList = new UserLs();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            RedisSyncer.savaData(REIDS_ID, userList.getList());
            LOG.info("Get All Success", GsonCustom.getInstance().toJson(users));
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Error get all user", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

    }
    @RequestMapping(value = "/add_user",
            method = RequestMethod.POST)
    public String createUser(@Valid @RequestBody User user) throws YamlException, FileNotFoundException {
        UserLs userList = new UserLs();
        LOG.info("add user: {}", GsonCustom.getInstance().toJson(user));

        if (!Validator.checkSum(user)) {
            return "03|CHECKSUM_FAIL";
        }
        userService.save(user);
        RedisSyncer.savaData(REIDS_ID, userList.getList());
        LOG.info("Add succsess");
        return "00|SUCCESS";
    }
}


