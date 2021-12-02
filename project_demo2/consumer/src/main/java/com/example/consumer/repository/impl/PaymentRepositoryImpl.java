package com.example.consumer.repository.impl;

import com.example.consumer.config.connectionDB.ConnectionDB;
import com.example.consumer.entity.Payment;
import com.example.consumer.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Log4j2
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    static final String INSERT_INTO = "INSERT INTO tbl_pay(" +
            "token_key,apiid,mobile,bank_code,account_no," +
            "pay_date,addtional_data,debit_amount,resp_code,resp_desc," +
            "trace_transfer,check_sum,order_code,user_name," +
            "real_amount,promotion_code,add_value)" +
            " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public Boolean save(Payment pay) {
        try (Connection con = ConnectionDB.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(INSERT_INTO);
            stmt.setString(1, pay.getTokenKey());
            stmt.setString(2, pay.getApiID());
            stmt.setString(3, pay.getMobile());
            stmt.setString(4, pay.getBankCode());
            stmt.setString(5, pay.getAccountNo());
            stmt.setString(6, pay.getPayDate());
            stmt.setString(7, pay.getAddtionalData());
            stmt.setDouble(8, pay.getDebitAmount());
            stmt.setString(9, pay.getRespCode());
            stmt.setString(10, pay.getRespDesc());
            stmt.setString(11, pay.getTraceTransfer());
            stmt.setString(12, pay.getCheckSum());
            stmt.setString(13, pay.getOrderCode());
            stmt.setString(14, pay.getUserName());
            stmt.setString(15, pay.getRealAmount());
            stmt.setString(16, pay.getPromotionCode());
            stmt.setString(17, pay.getAddValue());
            con.commit();
            return true;
        } catch (SQLException throwables) {
            log.error("Error! Insert data pay error:{}", throwables.getMessage());
        }
        return false;
    }
}