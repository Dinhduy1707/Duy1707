package com.example.controller;

import com.example.common.LogCommon;
import com.example.model.Product;
import com.example.service.ProductService;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hello")
public class ProductManagerController {
    private  static final Logger LOG = LogManager.getLogger(ProductManagerController.class);
    private ProductService productService;

    @Autowired
    public ProductManagerController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/get_products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProduct() {
        LOG.info("Get all product");

         List<Product> products = productService.findAllProduct();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOG.info("Get All Success");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@Validated @PathVariable("id") Integer id) {
        LOG.info("Get one products");
        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        LOG.info("Succsess get one products");
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add_products",
            method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, UriComponentsBuilder builder) {
        LOG.info("add producrs ");
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());

        LOG.info("Add succsess");
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/put_products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct( @Validated @PathVariable("id") Integer id, @RequestBody Product product) {
        LOG.info("update products ");
        Optional<Product> currentProduct = productService
                .findById(id);

        if (!currentProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Optional<Product> currentUser = productService.findById(id);
        currentUser.get().setTokenKey(product.getTokenKey());
        currentUser.get().setApiID(product.getApiID());
        currentUser.get().setMobie(product.getMobie());
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


        productService.save(currentProduct.get());
        LOG.info("Update success");
        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete_products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(  @PathVariable("id") Integer id) {
        LOG.info("Delele one product");
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(product.get());
        LOG.info("Remove succsess");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

