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
        System.out.println("addRoute works");
        Route route = new Route();
        CustomerFacade customerFacade = new CustomerFacade();
        RouteFacade routerFacade = new RouteFacade();
        
        Customer customerA = customerFacade.find(data.getCustomerA().getId());
        Customer customerB = customerFacade.find(data.getCustomerB().getId());
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
System.out.println("works zz");

        Route newlyRoute = new Route();
        newlyRoute = routerFacade.create(route);
        return Response.status(Response.Status.CREATED).entity(newlyRoute).build();
//        return Response.status(Response.Status.CREATED).entity("qwe").build();
    }
}
