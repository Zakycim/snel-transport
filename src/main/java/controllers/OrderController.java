package controllers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.Order;

import javax.json.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import java.util.Date;


@ApplicationPath("/api")
@Path("/orders")
public class OrderController extends Application {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelloMsg() {

        return "test";
    }

    /**
     *
     * @param message
     * @return
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(JsonObject data) {
        
//        Order order = new Order();
//        order.setName(data.getString("name"));
//        long customerId = data.getInt("customerId");
//        order.setCustomerId(customerId);
//        Double price = new Double(data.getString("price"));
//        order.setPrice(price);
//        Date orderDate = new Date();
//        order.setOrderDate(orderDate);
//        order.setStatus(data.getInt("status"));
//        String dbName = "snel-transport";
//
//        if (data.containsKey("environment")) {
//            if (data.getString("environment").equals("TEST")) {
//                dbName = "snel-transport-test";
//            }
//        }
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(dbName);
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        em.persist(order);
//        em.flush();
//        tx.commit();
//        em.close();
//        emf.close();
//
        System.out.println("ik zit in addorder");
        JsonObject obj = Json.createObjectBuilder().add("message", "Your order has been created.")
//                .add("id", order.getId()).add("name", data.getString("name")).build();
        .add("id", 1).add("name", "yes").build();

        return Response.status(Response.Status.CREATED).entity(obj).build();
    }
}