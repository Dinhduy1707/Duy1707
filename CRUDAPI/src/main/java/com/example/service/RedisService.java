package com.example.service;

public interface RedisService {
    boolean cacheHashset(String key, String field, String value);
}
