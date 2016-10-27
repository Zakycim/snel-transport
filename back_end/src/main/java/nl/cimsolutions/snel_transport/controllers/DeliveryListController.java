package nl.cimsolutions.snel_transport.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.DeliveryList;
import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.DeliveryListFacade;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("deliverylist")
public class DeliveryListController {
    DeliveryListFacade deliveryListFacade = new DeliveryListFacade();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeliveryList(DeliveryList data){
        TruckFacade truckFacade = new TruckFacade();
        OrdersFacade orderFacade = new OrdersFacade();
       
        
        Order order = orderFacade.find(data.getOrder().getId());
        Truck truck = truckFacade.find(data.getTruck().getId());
        
        DeliveryList deliveryList = new DeliveryList();
        DeliveryList newlyDeliveryList = new DeliveryList();
        
        deliveryList.setOrder(order);
        deliveryList.setTruck(truck);
        System.out.println("qweee");
        newlyDeliveryList = deliveryListFacade.create(deliveryList);
        System.out.println("qweee zz");
        
        return Response.status(Response.Status.OK).entity(newlyDeliveryList).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DeliveryList> getAllDeliveries() {
        System.out.println("coolll");
        List<DeliveryList> delliveries = deliveryListFacade.findAll();
        for (int i = 0; i < delliveries.size(); i++) {
            System.out.println(delliveries.get(i).getTruck().getLicensePlate());
            
        }
        System.out.println("coolll2");
        return delliveries;
    }
}
