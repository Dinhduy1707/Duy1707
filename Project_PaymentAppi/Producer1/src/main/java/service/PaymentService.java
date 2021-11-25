package service;

import common.CommonPool;
import common.JsonCustom;
import lombok.extern.log4j.Log4j2;
import model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class PaymentService {

    static final String GET_PAYMENT = "SELECT * FROM tblcustomer ";

    static final String INSERT_PAYMENT = "INSERT INTO tblcustomer (id, tokenKey,apiID,mobile,bankCode,accountNo,payDate,addtionalData,debitAmount,respCode,respDesc,traceTransfer,messageType," +
            "checkSum,oderCode,userName,realAmount,promotionCode,addValue) " +
            "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public List<Payment> getAll() {
        List<Payment> paymentList = new ArrayList<>();
        try (Connection connection = CommonPool.hikariDataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_PAYMENT)) {
            log.info("Begin get all data user ");
            try (ResultSet rs = stmt.executeQuery()) {
                log.info(connection.toString() + "......");
                while (rs.next()) {

                    Payment payments = new Payment();
                    payments.setId(rs.getInt("id"));
                    payments.setApiID(rs.getString("apiID"));
                    payments.setTokenKey(rs.getString("tokenKey"));
                    payments.setMobile(rs.getString("mobile"));
                    payments.setBankCode(rs.getString("bankCode"));
                    payments.setAccountNo(rs.getString("accountNo"));
                    payments.setPayDate(rs.getString("payDate"));
                    payments.setAddtionalData(rs.getString("addtionalData"));
                    payments.setDebitAmount(rs.getString("debitAmount"));
                    payments.setRespCode(rs.getString("respCode"));
                    payments.setRespDesc(rs.getString("respDesc"));
                    payments.setTraceTransfer(rs.getString("traceTransfer"));
                    payments.setMessageType(rs.getString("messageType"));
                    payments.setCheckSum(rs.getString("checkSum"));
                    payments.setOderCode(rs.getString("oderCode"));
                    payments.setUserName(rs.getString("userName"));
                    payments.setRealAmount(rs.getString("realAmount"));
                    payments.setPromotionCode(rs.getString("promotionCode"));
                    payments.setAddValue(rs.getString("addValue"));


                    paymentList.add(payments);
                    log.info("Get success {} :", JsonCustom.convertObjectToJson(payments));
                }
            }
            log.info("End get all data user");
        } catch (Exception e) {
            log.error("Error get data ", e);
        }
        return paymentList;
    }

    // insert data
    public Payment insertUser(Payment payment) {

        try (Connection connection = CommonPool.hikariDataSource.getConnection();

             PreparedStatement stmt = connection.prepareStatement(INSERT_PAYMENT)) {
            log.info("Begin addUse rwith data: " + JsonCustom.convertObjectToJson(payment));
            stmt.setInt(1, payment.getId());
            stmt.setString(2, payment.getTokenKey());
            stmt.setString(3, payment.getApiID());
            stmt.setString(4, payment.getMobile());
            stmt.setString(5, payment.getBankCode());
            stmt.setString(6, payment.getAccountNo());
            stmt.setString(7, payment.getPayDate());
            stmt.setString(8, payment.getAddtionalData());
            stmt.setString(9, payment.getDebitAmount());
            stmt.setString(10, payment.getRespCode());
            stmt.setString(11, payment.getRespDesc());
            stmt.setString(12, payment.getTraceTransfer());
            stmt.setString(13, payment.getMessageType());
            stmt.setString(14, payment.getCheckSum());
            stmt.setString(15, payment.getOderCode());
            stmt.setString(16, payment.getUserName());
            stmt.setString(17, payment.getRealAmount());
            stmt.setString(18, payment.getPromotionCode());
            stmt.setString(19, payment.getAddValue());
            stmt.execute();
            log.info("Insert database success");

        } catch (Exception e) {
            log.error("Error insert addUser: ", e);
        }
        return payment;
    }
}




