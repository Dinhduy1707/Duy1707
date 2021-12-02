package com.example.consumer.repository;

import com.example.consumer.entity.Payment;

import java.sql.SQLException;

public interface PaymentRepository {
    Boolean save(Payment payment) ;
}
