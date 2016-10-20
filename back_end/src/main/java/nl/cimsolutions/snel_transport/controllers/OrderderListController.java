package nl.cimsolutions.snel_transport.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrderListFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("orderlist")
public class OrderderListController {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderList> getAllorderlists() {
		OrderListFacade oFacade = new OrderListFacade();
        List<OrderList> os = oFacade.AssignTrucksToOrders();
        
        // wachten tot os uitgevoerd is en daarna deze functie aanroepen die orderListR returned.
//        List<OrderList> orderListR = oFacade.findAll();
        		
        
        return os;
    } 
	

}
