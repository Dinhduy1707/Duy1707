package com.example.consumer.repository;

import com.example.consumer.common.CommonRabbit;
import com.example.consumer.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {


}
