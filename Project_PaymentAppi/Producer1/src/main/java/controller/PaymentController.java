package controller;

import common.GsonCustom;
import common.KeyRedis;
import lombok.extern.log4j.Log4j2;
import model.Payment;
import model.PaymentList;
import service.PaymentService;
import syncer.Producer;
import syncer.RedisSyncer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log4j2
@Path("/api/payment")
public class PaymentController {
    PaymentService paymentService = new PaymentService();
    PaymentList paymentList = new PaymentList();
    Payment paymentRespose = new Payment();


    @GET
    @Path("/get_payment")
    @Produces(MediaType.APPLICATION_JSON)
    public PaymentList getUser(Payment payment)  {

        log.info("Begin call api  get_payment: {}"+ GsonCustom.getInstance().toJson(payment));
        paymentList.setList(paymentService.getAll());
        Producer.submit(paymentList.getList());
        log.debug("End call api get_payment");

        return paymentList;

    }


    @POST
    @Path("add_user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(Payment payment) throws Exception {

        log.info("Begin call api api_payment", GsonCustom.getInstance().toJson(paymentList.getList()));
        RedisSyncer.saveData(KeyRedis.MERCHANT_CODE_TERMINAL_ID, payment);
        paymentRespose = paymentService.insertUser(payment);
        log.debug("End call api_payment");

        return Response.ok(paymentRespose).build();
    }
}


