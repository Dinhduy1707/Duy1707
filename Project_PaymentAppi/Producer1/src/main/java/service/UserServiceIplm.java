package service;

import bean.User;
import common.CommonPool;
import common.JsonCustom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.UserDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIplm implements UserDao {

    private static final Logger LOG = LogManager.getLogger(UserServiceIplm.class);
    private static final String GET_USER = "SELECT  * FROM customerdb.tblcustomer ";
    private static final String INSERT_USER = "{INSERT  into tblcustomer (tokenKey,apiID,mobile,bankCode,accountNo,payDate,additionalData,debitAmount,respCode,respDesc,traceTransfer,messageType," +
            "checkSum,orderCode,userName,realAmount,promotionCode,addvalue) " +
            "value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";

    @Override
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();
        try (Connection connection = CommonPool.hikariDataSource.getConnection();

             PreparedStatement stmt = connection.prepareStatement(GET_USER)) {
            LOG.info("Begin get all data qr_terminal");
            ResultSet rs = null;
            rs = stmt.executeQuery();

            try {
                while (rs.next()) {
                    User users = new User();
                    users.setId(rs.getInt("id"));
                    users.setApiID(rs.getString("ApiID"));
                    users.setTokenKey(rs.getString("TokenKey"));
                    users.setMoblie(rs.getString("mobile"));
                    users.setBankCode(rs.getString("bankCode"));
                    users.setAccountNo(rs.getString("accountNo"));
                    users.setPayDate(rs.getString("payDate"));
                    users.setAddtionalData(rs.getString("addtionalData"));
                    users.setDebitAmount(rs.getInt("debitAmount"));
                    users.setRespCode(rs.getString("respCode"));
                    users.setRespDesc(rs.getString("respDesc"));
                    users.setTraceTransfer(rs.getString("traceTransfer"));
                    users.setMessageType(rs.getString("messageType"));
                    users.setCheckSum(rs.getString("checkSum"));
                    users.setOderCode(rs.getString("orderCode"));
                    users.setUserName(rs.getString("userName"));
                    users.setRealAmount(rs.getString("realAmount"));
                    users.setPromotionCode(rs.getString("promotionCode"));
                    users.setAddvalue(rs.getString("addValue"));

                    listUser.add(users);
                    LOG.info("Get success {} :", JsonCustom.convertObjectToJson(users));
                }
            } catch (Exception e) {
                LOG.info("End get all data user");
            }
        } catch (Exception e) {
            LOG.error("Error get data api get_user: ", e);
        }
        return listUser;
    }

    // insert
    @Override
    public User insertUser(User user) {

        try (Connection connection = CommonPool.hikariDataSource.getConnection();
             CallableStatement stmt = connection.prepareCall(INSERT_USER)) {
            LOG.info("Begin addUserwith data: " + JsonCustom.convertObjectToJson(user));
            stmt.setInt("id", user.getId());
            stmt.setString("TokenKey", user.getTokenKey());
            stmt.setString("ApiID", user.getApiID());
            stmt.setString("Mobile", user.getMoblie());
            stmt.setString("BankCode", user.getBankCode());
            stmt.setString("AccountNo", user.getAccountNo());
            stmt.setString("PayDate", user.getPayDate());
            stmt.setString("AddtinalData", user.getAddtionalData());
            stmt.setInt("DebitAmount", user.getDebitAmount());
            stmt.setString("RespCode", user.getRespCode());
            stmt.setString("RespDesc", user.getRespDesc());
            stmt.setString("TraceTransfer", user.getTraceTransfer());

            stmt.setString("MessageType", user.getMessageType());
            stmt.setString("CheckSum", user.getCheckSum());
            stmt.setString("OderCode", user.getOderCode());
            stmt.setString("UserName", user.getUserName());
            stmt.setString("RealAmount", user.getRealAmount());
            stmt.setString("PromotionCode", user.getPromotionCode());
            stmt.setString("Addvalue", user.getAddvalue());
            stmt.executeUpdate();
            user.setId(stmt.getInt("id"));
            LOG.info("Add success user {}:", JsonCustom.convertObjectToJson(user));
        } catch (Exception e) {
            LOG.error("Error insert addUser: ", e);
        }
        return user;
    }
}




