package nl.cimsolutions.snel_transport.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.google.gson.Gson;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrderListFacade;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("orderlist")
public class OrderListController {

    private OrderListFacade oFacade = new OrderListFacade();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAllorderlists() throws Exception {
        
        oFacade.AssignOrdersTolist();
        List<OrderList> orderListIds = null;//oFacade.AssignTrucksToOrders();
        return Response.status(Response.Status.CREATED).entity("").build();
    }
    
    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmOrderList() throws Exception {
        
        return oFacade.generateOrderList(oFacade.AssignTrucksToOrders());
    } 

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderList(OrderList[] orderLists) {
        System.out.println("yeyeye");
        OrderList orderlist = new OrderList();
        OrderList newlyOrderlist = new OrderList();
        for (int i = 0; i < orderLists.length; i++) {
            orderlist.setTruck(orderLists[i].getTruck());
            orderlist.setOrder(orderLists[i].getOrder());
            newlyOrderlist = oFacade.create(orderlist);
        }
        return Response.status(Response.Status.CREATED).entity(newlyOrderlist).build();
        
    }
}