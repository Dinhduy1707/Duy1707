package com.example.controller;

import com.esotericsoftware.yamlbeans.YamlException;
import com.example.check.GsonCustom;

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
    private UserService productService;

    @Autowired
    public UserManagerController(UserService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllProduct() {
        UserLs userList = null;
        try {
            LOG.info("Get all user");
            List<User> users = productService.findAllProduct();
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
    public ResponseEntity<User> getProductById(@Validated @PathVariable("id") int id) {
        try {
            LOG.info("Get one user by id: {}", id);
            Optional<User> product = productService.findById(id);
            ResponseEntity<User> respnse;
            if (product.isEmpty()) {
                respnse = new ResponseEntity<>(product.get(),
                        HttpStatus.NO_CONTENT);
            }
            respnse = new ResponseEntity<>(product.get(), HttpStatus.OK);
            LOG.info("Succsess get one user: {}", GsonCustom.getInstance().toJson(product));
            return respnse;
        } catch (Exception e) {
            LOG.error("Error get one user", e);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/add_user",
            method = RequestMethod.POST)
    public String createProduct(@Valid @RequestBody User user) throws YamlException, FileNotFoundException {
        UserLs userList = new UserLs();
        LOG.info("add user: {}", GsonCustom.getInstance().toJson(user));

        if (!Validator.checkSum(user)) {
            return "03|CHECKSUM_FAIL";
        }

        productService.save(user);
        RedisSyncer.savaData(REIDS_ID, userList.getList());
        LOG.info("Add succsess");
        return "00|SUCCESS";
    }

    @RequestMapping(value = "/put_user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateProduct(@Validated @PathVariable("id") Integer id, @RequestBody User user) {
        try {
            LOG.info("update user id{} , user {} :  ", id, GsonCustom.getInstance().toJson(user));
            if (!Validator.checkSum(user)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Optional<User> products = productService.findById(id);

            ResponseEntity<User> reponse;

            if (!products.isPresent()) {
                reponse = new ResponseEntity<>(products.get(), HttpStatus.NO_CONTENT);
            }
            Optional<User> currentUser = productService.findById(id);
            currentUser.get().setTokenKey(user.getTokenKey());
            currentUser.get().setApiID(user.getApiID());
            currentUser.get().setMobile(user.getMobile());
            currentUser.get().setBankCode(user.getBankCode());
            currentUser.get().setAccountNo(user.getAccountNo());
            currentUser.get().setPayDate(user.getPayDate());
            currentUser.get().setAddtionalData(user.getAddtionalData());
            currentUser.get().setDebitAmount(user.getDebitAmount());
            currentUser.get().setRespDesc(user.getRespDesc());
            currentUser.get().setRespCode(user.getRespCode());
            currentUser.get().setTraceTransfer(user.getTraceTransfer());
            currentUser.get().setMessageType(user.getMessageType());
            currentUser.get().setCheckSum(user.getCheckSum());
            currentUser.get().setOderCode(user.getOderCode());
            currentUser.get().setUserName(user.getUserName());
            currentUser.get().setRealAmount(user.getRealAmount());
            currentUser.get().setPromotionCode(user.getPromotionCode());
            currentUser.get().setAddValue(user.getAddValue());
            productService.save(products.get());
            reponse = new ResponseEntity<>(products.get(), HttpStatus.OK);
            LOG.info("Update success id {}, user {}", id, GsonCustom.getInstance().toJson(user));
            return reponse;

        } catch (Exception e) {
            LOG.error("Error update ", e);
            return null;
        }
    }

    @RequestMapping(value = "/delete_user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteProduct(@PathVariable("id") Integer id) {
        LOG.info("Delele one user id {} :", id);
        try {
            Optional<User> product = productService.findById(id);

            ResponseEntity<User> reponse;

            if (product.isEmpty()) {
                reponse = new ResponseEntity<>(product.get(), HttpStatus.NO_CONTENT);
            }
            reponse = new ResponseEntity<>(product.get(), HttpStatus.OK);
            productService.remove(product.get());
            LOG.info("Remove succsess one user: {}", GsonCustom.getInstance().toJson(product));
            return reponse;
        } catch (Exception e) {
            LOG.error("Error remove ", e);
            return null;
        }
    }
}


