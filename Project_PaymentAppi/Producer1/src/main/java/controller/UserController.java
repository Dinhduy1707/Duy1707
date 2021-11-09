package controller;

import bean.User;
import bean.UserLs;
import common.CommonPool;
import common.GsonCustom;
import common.LogCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import service.UserServiceIplm;
import syncer.Producer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    UserServiceIplm userServiceIplm = new UserServiceIplm();

    @GET
    @Path("/get_user")
    @Produces(MediaType.APPLICATION_JSON)
    public UserLs getQrTerminal() {
        try {
            UserLs userLs = null;

            LOG.info("Begin call api get_user");
            userLs = new UserLs();
            userLs.setList(userServiceIplm.getAll());

            LOG.info("Begin success user {} :", GsonCustom.getInstance().toJson(userLs));
            Producer.submit(userLs.getList());
            return userLs;
        } catch (Exception e) {
            LOG.error("Error begin user", e);

            return null;
        }
    }

    @POST
    @Path("add_user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        try {
            LOG.info("Begin call api add_user :", GsonCustom.getInstance().toJson(user));
            User userResponse = null;
            userResponse = userServiceIplm.insertUser(userResponse);
            LOG.info("Add susscess user {} :", GsonCustom.getInstance().toJson(user));

            LOG.info("End call api add_user");

            return Response.ok(userResponse).build();
        } catch (Exception e) {
            LOG.error("Error add user", e);
            return null;
        }
    }
}
