package com.example.repository;

import com.example.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductDaoIplm implements ProductDao {

    private  static  final Logger LOG = LogManager.getLogger(ProductDaoIplm.class);
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "PRODUCT";


    @Override
    public boolean saveProduct(Product product) {

        try {
            LOG.info(" save product");
            redisTemplate.opsForHash().put(KEY, product.getId(), product);
            return true;
        } catch (Exception e) {
            LOG.error("error save" +e);
            return false;
        }
    }

    @Override
    public List<Product> fetchAllProduct() {
        LOG.info("ferch ALl Product");
        List<Product> products;
        products  = redisTemplate.opsForHash().values(KEY);

        LOG.info("Success");
        return  products;
    }

    @Override
    public Product fetchProductById(long id) {
        LOG.info("ferch by id Product");
        Product product;
        product = (Product) redisTemplate.opsForHash().get(KEY, id);
        LOG.info("Success By id");
        return product;
    }

    @Override
    public boolean deleteProduct(long id) {
        LOG.info("Delete Product");
        try {
            redisTemplate.opsForHash().delete(KEY,id);
            return true;
        } catch (Exception e) {
            LOG.error("Error delete " + e);
            return false;
        }
    }

    @Override
    public boolean updateProduct(long id, Product product) {
        LOG.info("Update  Product");
        try {
            redisTemplate.opsForHash().put(KEY, id, product);
            return true;
        } catch (Exception e) {
            LOG.error("Error update product" + e);
            return false;
        }
    }
    }

