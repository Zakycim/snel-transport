package nl.cimsolutions.snel_transport.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.OrderListFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("orderlist")
public class OrderListController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderList> getOrderList() {
    	OrderListFacade olf = new OrderListFacade();
    	List<OrderList> orderlist = olf.getAllOrderLists();
    	for(int i=1; i < orderlist.size(); i++){
            System.out.println("Truck is: " + orderlist.get(i).getTrucks());
       }
    	// System.out.println("Orderlist object: "+orderlist);
        return orderlist;//olf.findAll();
    }
    
    @GET
    @Path("/truck")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderList> getOrdersByTruck() {
    	OrderListFacade orderListFacade = new OrderListFacade();
        
        List<OrderList> orderlist = orderListFacade.getOrdersByTruck();
             
        return orderlist;
        
    }
    
    // Post ontvangen met @POST Dit is het verdelen van de orders over de trucks
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void generateOrderList(boolean generate){
    	if (generate = true){
    		TruckFacade truck = new TruckFacade();
    		List<Truck> trucks = truck.getAllTruckPlates();
    		OrdersFacade order = new OrdersFacade();
    		List<Orders> orders = null;
    		
    	}
    	//return Response.status(Response.Status.CREATED).entity(null).build();      ;
    }
    	// Controleren of boolean true is
    
    		// Alle trucks ophalen
    
    		// Alle Orders ophalen van vorige dag bla bla
    
    		// Elke truck heeft 8 uur beschikbaar 8 * 60 - 30 terug rijden
    
    		// Elke order is 1 uur ( 60 min)
    
    		// Orders verdelen over een truck door bijvoorbeeld een loop 30 + een order (60) tot een truck onder de 480 is
    
    		// nieuwe truck pakken zelfde principe als hierboven
    
    		// opslaan in database
    
    		// de get kan nu opgeroepen worden met de juiste data erin ( miss opvragen door middel van datum)

}
