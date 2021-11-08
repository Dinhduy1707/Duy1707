package com.example.controller;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.example.check.GsonCustom;
import com.example.common.LogCommon;

import com.example.model.Product;
import com.example.service.ProductService;
import com.example.valida.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//fixme truoc khi dung bat kia 1 object gi phai check khac null

@RestController
@RequestMapping("/hello")
public class ProductManagerController {
    private static final Logger LOG = LogManager.getLogger(ProductManagerController.class);
    private ProductService productService;

    @Autowired
    public ProductManagerController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/get_products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProduct() {
        try {
            LOG.info("Get all product");
            List<Product> products = productService.findAllProduct();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Get All Success", GsonCustom.getInstance().toJson(products));
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Error get all product", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/get_products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@Validated @PathVariable("id") int id) {
        try {
            LOG.info("Get one products by id: {}", id);
            Optional<Product> product = productService.findById(id);
            ResponseEntity<Product> respnse;
            if (product.isEmpty()) {
                respnse = new ResponseEntity<>(product.get(),
                        HttpStatus.NO_CONTENT);
            }
            respnse = new ResponseEntity<>(product.get(), HttpStatus.OK);
            LOG.info("Succsess get one products: {}", GsonCustom.getInstance().toJson(product));
            return respnse;
        } catch (Exception e) {
            LOG.error("Error get one product", e);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/add_products",
            method = RequestMethod.POST)
    public String createProduct(@Valid @RequestBody Product product) throws YamlException, FileNotFoundException {
        LOG.info("add product: {}", GsonCustom.getInstance().toJson(product));

        if (!Validator.checkSum(product)){
            return "03|CHECKSUM_FAIL";
       }

        productService.save(product);
        LOG.info("Add succsess");
        return "00|SUCCESS";
    }

    @RequestMapping(value = "/put_products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@Validated @PathVariable("id") Integer id, @RequestBody Product product) {
        try {
            LOG.info("update products id{} product {} :  ", id, GsonCustom.getInstance().toJson(product));
            if (!Validator.checkSum(product)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Optional<Product> products = productService.findById(id);

            ResponseEntity<Product> reponse;

            if (!products.isPresent()) {
                reponse = new ResponseEntity<>(products.get(), HttpStatus.NO_CONTENT);
            }
            Optional<Product> currentUser = productService.findById(id);
            currentUser.get().setTokenKey(product.getTokenKey());
            currentUser.get().setApiID(product.getApiID());
            currentUser.get().setMobile(product.getMobile());
            currentUser.get().setBankCode(product.getBankCode());
            currentUser.get().setAccountNo(product.getAccountNo());
            currentUser.get().setPayDate(product.getPayDate());
            currentUser.get().setAddtionalData(product.getAddtionalData());
            currentUser.get().setDebitAmount(product.getDebitAmount());
            currentUser.get().setRespDesc(product.getRespDesc());
            currentUser.get().setRespCode(product.getRespCode());
            currentUser.get().setTraceTransfer(product.getTraceTransfer());
            currentUser.get().setMessageType(product.getMessageType());
            currentUser.get().setCheckSum(product.getCheckSum());
            currentUser.get().setOderCode(product.getOderCode());
            currentUser.get().setUserName(product.getUserName());
            currentUser.get().setRealAmount(product.getRealAmount());
            currentUser.get().setPromotionCode(product.getPromotionCode());
            currentUser.get().setAddValue(product.getAddValue());
            productService.save(products.get());
            reponse = new ResponseEntity<>(products.get(), HttpStatus.OK);
            LOG.info("Update success id {}, product {}", id, GsonCustom.getInstance().toJson(product));
            return reponse;

        }catch (Exception e) {
            LOG.error("Error update ", e);
            return null ;
        }
    }

    @RequestMapping(value = "/delete_products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
        LOG.info("Delele one product id {} :", id);
        try {
            Optional<Product> product = productService.findById(id);

            ResponseEntity<Product> reponse;

            if (product.isEmpty()) {
                reponse = new ResponseEntity<>(product.get(), HttpStatus.NO_CONTENT);
            }
            reponse = new ResponseEntity<>(product.get(), HttpStatus.OK);
            productService.remove(product.get());
            LOG.info("Remove succsess one products: {}", GsonCustom.getInstance().toJson(product));
            return reponse;
        }catch (Exception e) {
            LOG.error("Error remove ", e);
            return null;
        }
    }

//   @Autowired
//    private ConverterYaml config;
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String DemoYaml() {
//        String result = "";
//
//        for (int i = 0; i < config.getBanks().size(); i++) {
//            result = result + config.getBanks().get(i).Infor()+ "\n";
//        }
//        System.out.println(result + "=============");
//        return result;
//    }
}

