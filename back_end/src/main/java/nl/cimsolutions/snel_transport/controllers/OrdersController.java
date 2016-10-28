
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
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.StatusFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;
import nl.cimsolutions.snel_transport.services.OrderLineFacade;
import nl.cimsolutions.snel_transport.services.OrderListFacade;

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

    OrdersFacade orderFacade = new OrdersFacade();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getAllOrders() {
        System.out.println("getallorders");
//        OrdersFacade orderFacade = new OrdersFacade();
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
        
        // Check if order is empty
        if(order == null){
        	return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't find customer").build();
        } else {
        	return Response.status(Response.Status.OK).entity(order).build();
        }

    }
    
    @GET
    @Path("/deliverylist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderList> getOrderList() {
    	System.out.println("getallorders");
    	OrderListFacade orderListFacade = new OrderListFacade();
	      List<OrderList> orderlists = orderListFacade.findAll();// findAll();

	      return orderlists;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders data) {
        if(data.getCustomer().getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customer ID is required").build();
        }
        
        CustomerFacade customerFacade = new CustomerFacade();
        Customer customer = customerFacade.find(data.getCustomer().getId());
        
        if(customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerID wasn't found").build();
        }
        
        Orders order = new Orders();
        StatusFacade statusFacade = new StatusFacade();

        Status status = statusFacade.find(1L);
        order.setCustomer(customer);

        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        order.setStatus(status);
        
        if (data.getOrderLines() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Order lines are required").build();
        }
        
        List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
        Product product = new Product();
        ProductFacade productFacade = new ProductFacade();
        
        for (int i = 0; i < data.getOrderLines().size(); i++) {
            OrderLine orderLine = new OrderLine();
            product = productFacade.find(data.getOrderLines().get(i).getProduct().getId());
            if(product == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("product ID wasn't found").build();
            }
            
            orderLine.setProduct(product);
            orderLine.setAmount(data.getOrderLines().get(i).getAmount());
            orderLines.add(orderLine);
        }
        
        order.setOrderLines(orderLines);
        

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
    
    @POST
    @Path("/deliverylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderList(OrderList data) {
    	OrderListFacade orderListFacade = new OrderListFacade();
    	TruckFacade truckFacade = new TruckFacade();
    	OrdersFacade ordersFacade = new OrdersFacade();
        // Print all the array elements
//        OrderList orderList = new OrderList();
//        Orders order = new Orders();
//        Truck truck = new Truck();
//        truck.setId(data.getTruck().getId());
//        truck = truckFacade.find(truck.getId());
//        orderList.setOrder(order);
//        orderList.setTruck(data.getTruck());
//        orderListFacade.create(orderList);
    	
    	// calculatie van pallavi aantal orders voor 10 uur
        int pallavidatumshizzle = 5;
        
        for (int i = 0; i < pallavidatumshizzle; i++) {
        	Orders order = new Orders();
        	OrderList orderList = new OrderList();
        	order.setId((long)i+1);
        	orderList.setOrder(order);
            orderList.setTruck(data.getTruck());
            orderListFacade.create(orderList);
        }
        
        // Lezen met welk nummer de eerste orderlist begint
        
        // roep method op van khaoula en meesturen in parameter
//        verdeelOrders(startInt)


        return Response.status(Response.Status.CREATED).entity("blabla").build();
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