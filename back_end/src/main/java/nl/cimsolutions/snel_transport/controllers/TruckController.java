package nl.cimsolutions.snel_transport.controllers;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrderFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("trucks")
public class TruckController {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Truck> getTrucks() {
        TruckFacade tf = new TruckFacade();
        return tf.getAllTrucks();
        
    }
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder() {
        Truck truck = new Truck(1L, "bla", true);//(data.getId(), data.getLicensePlate(), true);
        String dbName = "snel-transport";
        
        TruckFacade truckFacade = new TruckFacade();
         
        return Response.status(Response.Status.CREATED).entity(truckFacade.create(truck)).build();   
    }
}
