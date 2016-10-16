
package nl.cimsolutions.snel_transport.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.OrderFacade;
import nl.cimsolutions.snel_transport.services.OrderLineFacade;

/**
 * Root resource (exposed at "orders" path)
 */
@Path("orders")
public class OrderController {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() {
        OrderFacade orderFacade = new OrderFacade();

        List<Order> orders = orderFacade.findAll();

        return orders;
    } 
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Order data) {
        System.out.println("customer jajaj" );
        Order order = new Order();
        
        if(data.getCustomerId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customer ID is required").build();
        }
        
        CustomerFacade customerFacade = new CustomerFacade();
        if(data.getEnv() != null){
            order.setEnv(data.getEnv());    
//            customerFacade = new CustomerFacade("snel-transport-test");
        } else{
            
        }
        long customerId = data.getCustomerId();
        
        //find method gaat naar DEV db daarom kan je je test ook niet laten slagen..
        Customer customer = customerFacade.find(customerId);
     
        if(customer == null) {
            System.out.println("customer is null" );
            return Response.status(Response.Status.BAD_REQUEST).entity("customer ID wasn't found").build();
        }
        
        order.setCustomerId(customerId);
        
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        order.setStatus(1);
        
        if(data.getOrderLines() != null) {
            order.setOrderLines(data.getOrderLines());
        }
      
        
        OrderFacade orderFacade = new OrderFacade();
        
        Order newlyOrder = new Order();
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
    public Order getOrderLines(@PathParam("id") long id) {
        OrderFacade orderFacade = new OrderFacade();
        
        Order order = orderFacade.find(id);
             
        return order;//Response.status(Response.Status.CREATED).entity(value).build();
        
    }
}