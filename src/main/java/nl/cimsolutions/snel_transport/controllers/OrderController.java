package nl.cimsolutions.snel_transport.controllers;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Order;

/**
 * Root resource (exposed at "orders" path)
 */
@Path("orders")
public class OrderController {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it orders!";
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order) {
        
        System.out.println("orderz "+ order.getName());
        Order newOrder = new Order();
        newOrder.setName(order.getName());
        long customerId = order.getCustomerId();
        newOrder.setCustomerId(customerId);
        Double price = new Double(order.getPrice());
        newOrder.setPrice(price);
        Date orderDate = new Date();
        newOrder.setOrderDate(orderDate);
        newOrder.setStatus(order.getStatus());
        String dbName = "snel-transport";
//        
//        if(data.containsKey("environment")) {
//            if(data.getString("environment").equals("TEST") ){
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
//        JsonObject obj = Json.createObjectBuilder().
//                add("message", "Your order has been created.").
//                add("id", order.getId()).
//                add("name", data.getString("name")).
//                build();
        
//        JsonObject obj = Json.createObjectBuilder().
//                add("message", "Your order has been created.").
//                build();
       
        return Response.status(Response.Status.CREATED).entity(order).build();      
    }
}
