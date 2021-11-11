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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//fixme truoc khi dung bat kia 1 object gi phai check khac null

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

    @RequestMapping(value = "/get_user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@Validated @PathVariable("id") int id) {
        try {
            LOG.info("Get one user by id: {}", id);
            Optional<User> user = userService.findById(id);
            ResponseEntity<User> respnse;
            if (user.isEmpty()) {
                respnse = new ResponseEntity<>(user.get(),
                        HttpStatus.NO_CONTENT);
            }
            respnse = new ResponseEntity<>(user.get(), HttpStatus.OK);
            LOG.info("Succsess get one user: {}", GsonCustom.getInstance().toJson(user));
            return respnse;
        } catch (Exception e) {
            LOG.error("Error get one user", e);
            return new ResponseEntity<>(HttpStatus.OK);
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

    @RequestMapping(value = "/put_user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@Validated @PathVariable("id") Integer id, @RequestBody User user) {
        LOG.info("update user id{} , user {} :  ", id, GsonCustom.getInstance().toJson(user));
        Optional<User> currentUser = userService.findById(id);
        if (currentUser.isPresent()) {
            User user1 = currentUser.get();
            user1.setTokenKey(user.getTokenKey());
            user1.setTokenKey(user.getTokenKey());
            user1.setApiID(user.getApiID());
            user1.setMobile(user.getMobile());
            user1.setBankCode(user.getBankCode());
            user1.setAccountNo(user.getAccountNo());
            user1.setPayDate(user.getPayDate());
            user1.setAddtionalData(user.getAddtionalData());
            user1.setDebitAmount(user.getDebitAmount());
            user1.setRespDesc(user.getRespDesc());
            user1.setRespCode(user.getRespCode());
            user1.setTraceTransfer(user.getTraceTransfer());
            user1.setMessageType(user.getMessageType());
            user1.setCheckSum(user.getCheckSum());
            user1.setOderCode(user.getOderCode());
            user1.setUserName(user.getUserName());
            user1.setRealAmount(user.getRealAmount());
            user1.setPromotionCode(user.getPromotionCode());
            user1.setAddValue(user.getAddValue());
            userService.save(user1);
        } else {


        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/delete_user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        LOG.info("Delele one user id {} :", id);
        try {
            Optional<User> user = userService.findById(id);

            ResponseEntity<User> reponse;

            if (user.isEmpty()) {
                reponse = new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
            }
            reponse = new ResponseEntity<>(user.get(), HttpStatus.OK);
            userService.remove(user.get());
            LOG.info("Remove succsess one user: {}", GsonCustom.getInstance().toJson(user));
            return reponse;
        } catch (Exception e) {
            LOG.error("Error remove  ", e);
            return null;
        }
    }
}


