
package nl.cimsolutions.snel_transport.controllers;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.OrderLineFacade;

/**
 * Root resource (exposed at "orders" path)
 */
@Path("orders")
public class OrdersController {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getAllOrders() {
        OrdersFacade orderFacade = new OrdersFacade();

        List<Orders> orders = orderFacade.getAllOrders();//findAll();

        return orders;
    } 
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders data) {
        Orders order = new Orders();
        
        long customerId = data.getCustomerId();
        order.setCustomerId(customerId);
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        order.setStatus(data.getStatus());
        order.setOrderLines(data.getOrderLines());
        String dbName = "snel-transport";
        
//        if(data.containsKey("environment")) {
//            if(data.getString("environment").equals("TEST") ){
//                dbName = "snel-transport-test";
//            }
//        }
        
        OrdersFacade orderFacade = new OrdersFacade();
        
        Orders newlyOrder = new Orders();
        newlyOrder = orderFacade.create(order);
         
        return Response.status(Response.Status.CREATED).entity(newlyOrder).build();      
    }
    
    
    @POST
    @Path("/orderlines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderLine(OrderLine[] data) {
        OrderLineFacade orderLineFacade = new OrderLineFacade();
        // Print all the array elements
        for (int i = 0; i < data.length; i++) {
            OrderLine orderLine = new OrderLine();
            orderLine.setOrderId(data[i].getOrderId());
            orderLine.setProductId(data[i].getProductId());
            orderLine.setAmount(data[i].getAmount());
            orderLineFacade.create(orderLine);
        }
        
        return Response.status(Response.Status.CREATED).entity(data).build();
        
    }
    
    @GET
    @Path("/{id}/orderlines")
    @Produces(MediaType.APPLICATION_JSON)
    public Orders getOrderLines(@PathParam("id") long id) {
        OrdersFacade orderFacade = new OrdersFacade();
        
        Orders order = orderFacade.find(id);
             
        return order;//Response.status(Response.Status.CREATED).entity(value).build();
        
    }
}