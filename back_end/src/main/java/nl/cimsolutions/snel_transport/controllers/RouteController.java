package nl.cimsolutions.snel_transport.controllers;

import java.sql.Time;
import java.time.Duration;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Route;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.RouteFacade;

@Path("routes")
public class RouteController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoute(Route data) {
        System.out.println("wassuppp2");
        if(data.getCustomerA() == null || data.getCustomerB() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerA and/or CustomerB object is required")
                    .build();
        }
        if(data.getCustomerA().getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerA ID is required").build();
        }
        if(data.getCustomerB().getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerB ID is required").build();
        }
        
        System.out.println("addRoute works");
        Route route = new Route();
        CustomerFacade customerFacade = new CustomerFacade();
        RouteFacade routerFacade = new RouteFacade();
        
        Customer customerA = customerFacade.find(data.getCustomerA().getId());
        if(customerA == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerA ID wasn't found").build();
        }
        
        Customer customerB = customerFacade.find(data.getCustomerB().getId());
        if(customerB == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerB ID wasn't found").build();
        }
        
        route.setCustomerA(customerA);
        route.setCustomerB(customerB);
        
        
        //Splits each element between a colon
        String[] parts = data.getDuration().split("\\:");
        System.out.println("parts[1] "+ parts[1]);
        
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        int hoursInSeconds = hours * 3600;
        int minutesInSeconds= minutes * 60;
        
        String duration = String.format("%d:%02d:%02d", hoursInSeconds / 3600, (minutesInSeconds % 3600) / 60, (seconds % 60));
        route.setDuration(duration);
        route.setDistance(data.getDistance());
        
        Route newlyRoute = new Route();
        newlyRoute = routerFacade.create(route);
        return Response.status(Response.Status.CREATED).entity(newlyRoute).build();
    }
}
