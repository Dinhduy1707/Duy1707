package startup;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.CommonPool;
import common.GsonCustom;
import lombok.extern.log4j.Log4j2;
import model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import service.PaymentService;
import utils.ConnectionDB;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ClientStartup {

    private final static String uri = "https://api.foodbook.vn/ipos/ws/xpartner/callback/vnpay";


    private final static PaymentService userServiceIplm = new PaymentService();


    public static void main(String[] args) throws JsonProcessingException {


        CommonPool.hikariDataSource = ConnectionDB.getDataSource();
        createPayment();
    }

    private static void createPayment() {
        log.info("Begin build client insert data with uri:" + uri);
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(uri);

        Payment payment = new Payment();
        payment.setAccountNo("12");
        payment.setOderCode("12");
        payment.setUserName("duy");
        payment.setBankCode("122");
        payment.setTokenKey("122");
        payment.setId(1707777777);
        payment.setAddtionalData("222");
        payment.setAddtionalData("12");
        payment.setDebitAmount("12");
        payment.setMessageType("2");
        payment.setMobile("1222222");
        payment.setPayDate("19991212202324");
        payment.setTraceTransfer("12");
        payment.setRespCode("1");
        payment.setRealAmount("1223");
        payment.setApiID("1234");
        payment.setRespDesc("1234");
        payment.setCheckSum("3333");
        payment.setAddtionalData("12222");
        payment.setPromotionCode("12");
        payment.setAddValue("12");

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(payment, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
        System.out.println(payment);
        log.info("Data" + GsonCustom.getInstance().toJson(payment));

        insertData(payment);

    }

    private static void insertData(Payment payment) {
        userServiceIplm.insertUser(payment);
    }
}





