
package nl.cimsolutions.snel_transport.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.StatusFacade;
import nl.cimsolutions.snel_transport.services.OrderLineFacade;

/**
 * Root resource (exposed at "orders" path)
 */
@Path("orders")
public class OrdersController {
    OrdersFacade orderFacade = new OrdersFacade();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getAllOrders() {
        List<Orders> orders = orderFacade.findAll();// findAll();
        return orders;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") long id) {
        OrdersFacade orderFacade = new OrdersFacade();

        Orders order = orderFacade.find(id);
        
        // Check if order is empty
        if(order == null){
        	return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't find customer").build();
        } else {
        	return Response.status(Response.Status.OK).entity(order).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders data) {
        Orders order = new Orders();
        try {
            order = order.completeFlow(data);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        
        return Response.status(Response.Status.CREATED).entity(order).build();
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
            orderLine.setProduct(data[i].getProduct());
            orderLine.setAmount(data[i].getAmount());
            orderLineFacade.create(orderLine);
        }

        return Response.status(Response.Status.CREATED).entity(data).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editOrder(Orders data) {
    	Orders order = new Orders();
    	Orders editedOrder = new Orders();
    	System.out.println("editOrder");
    	System.out.println("data id " + data.getId());
    	
        order = orderFacade.find(data.getId());
        order.setStatus(data.getStatus());
        
//        order = orderFacade.edit(order);
//    	return Response.status(Response.Status.CREATED).entity(order).build();
        try {
            editedOrder = orderFacade.edit(order);
        	return Response.status(Response.Status.CREATED).entity(editedOrder).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't update order status").build();
		}
    }

    @GET
    @Path("/{id}/orderlines")
    @Produces(MediaType.APPLICATION_JSON)
    public Orders getOrderLines(@PathParam("id") long id) {
//        OrdersFacade orderFacade = new OrdersFacade();

        Orders order = orderFacade.find(id);

        return order;// Response.status(Response.Status.CREATED).entity(value).build();

    }
}