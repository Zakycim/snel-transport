package nl.cimsolutions.snel_transport.controllers;

import java.util.ArrayList;
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

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("trucks")
public class TruckController {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Truck> getAllTrucks() {
		TruckFacade truckFacade = new TruckFacade();

        List<Truck> trucks = truckFacade.getAllTrucks();//findAll();

        return trucks;
    } 
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTruck(Truck data) {
        Truck truck = new Truck();//(data.getId(), data.getLicensePlate(), true);
        truck.setLicensePlate("abcdefg");
        truck.setAvailable(true);
        
        System.out.println("addtruck");
        for (int i = 0; i < data.getOrderList().size(); i++) {
            
            System.out.println("de truck id");
            System.out.println(data.getOrderList().get(i).getTruckId());
            System.out.println(data.getOrderList().get(i).getTruckId());
            System.out.println("de order id");
            System.out.println(data.getOrderList().get(i).getOrder().getId());
           
        }
    
        truck.setOrderList(data.getOrderList());
        
        TruckFacade truckFacade = new TruckFacade();
         
        return Response.status(Response.Status.CREATED).entity(truckFacade.create(truck)).build(); 
    }
}
