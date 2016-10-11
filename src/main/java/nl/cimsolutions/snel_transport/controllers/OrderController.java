
package nl.cimsolutions.snel_transport.controllers;

import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.services.OrderFacade;
import nl.cimsolutions.snel_transport.services.OrderLineFacade;

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

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response post(Order order) {
//      
//        
//        JsonObject obj = Json.createObjectBuilder().
//              add("message", "Your order has been created.").
//              build();
//      return Response.status(Response.Status.CREATED).entity(order).build();      
//    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Order data) {
        Order order = new Order();
        order.setName(data.getName());
        
        long customerId = data.getCustomerId();
        order.setCustomerId(customerId);
        Double price = new Double(data.getPrice());
        order.setPrice(price);
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        order.setStatus(data.getStatus());
        String dbName = "snel-transport";
        
//        if(data.containsKey("environment")) {
//            if(data.getString("environment").equals("TEST") ){
//                dbName = "snel-transport-test";
//            }
//        }
        
        OrderFacade orderFacade = new OrderFacade();
        
        Order newlyOrder = new Order();
        newlyOrder = orderFacade.create(order);
         
        return Response.status(Response.Status.CREATED).entity(newlyOrder).build();      
    }
    
    
    @POST
    @Path("/orderlines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderLine(OrderLine[] data) {
         
        System.out.println("ye "+ data);
        // Print all the array elements
        for (int i = 0; i < data.length; i++) {
           System.out.println(data[i].getOrderId() + " qwe");
            OrderLine orderLine = new OrderLine();
            orderLine.setOrderId(data[i].getOrderId());
            orderLine.setProductId(data[i].getProductId());
            orderLine.setAmount(data[i].getAmount());
            
            OrderLineFacade orderLineFacade = new OrderLineFacade();
            
            OrderLine newlyOrder = new OrderLine();
            newlyOrder = orderLineFacade.create(orderLine);
        }
        
        return Response.status(Response.Status.CREATED).entity(data).build();
        
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderLines(OrderLine[] data, @PathParam("id") int id) {
        
        System.out.println("ye "+ data);
        System.out.println("ye id "+ id);

        
        return Response.status(Response.Status.CREATED).entity(data).build();
        
    }
}