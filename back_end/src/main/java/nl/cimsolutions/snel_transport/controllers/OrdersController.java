
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
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.models.Orders;
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
        System.out.println("getallorders");
        OrdersFacade orderFacade = new OrdersFacade();
        List<Orders> orders = orderFacade.findAll();// findAll();

        System.out.println(orders);
        System.out.println("getallorders end");
        return orders;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") long id) {
        OrdersFacade orderFacade = new OrdersFacade();

        Orders order = orderFacade.find(id);

        return Response.status(Response.Status.OK).entity(order).build();
        // return
        // order;//Response.status(Response.Status.CREATED).entity(value).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders data) {

        Orders order = new Orders();
        // if(data.getCustomerId() == null) {
        // return Response.status(Response.Status.BAD_REQUEST).entity("customer
        // ID is required").build();
        // }

        CustomerFacade customerFacade = new CustomerFacade();

        Customer customer = new Customer();
        customer = data.getCustomer();

        // Customer customer = customerFacade.find(customerId);
        //
        // if(customer == null) {
        // System.out.println("customer is null" );
        // return Response.status(Response.Status.BAD_REQUEST).entity("customer
        // ID wasn't found").build();
        // }

        order.setCustomer(customer);

        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        order.setStatus(1);

        if (data.getOrderLines() != null) {
            order.setOrderLines(data.getOrderLines());
        }

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
            orderLine.setProduct(data[i].getProduct());
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

        return order;// Response.status(Response.Status.CREATED).entity(value).build();

    }
}