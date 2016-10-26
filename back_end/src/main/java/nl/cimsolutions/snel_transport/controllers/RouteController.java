package nl.cimsolutions.snel_transport.controllers;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Route;
import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.RouteFacade;

@Path("routes")
public class RouteController {
    RouteFacade routerFacade = new RouteFacade();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoute(Route data) {
        if (data.getCustomerA() == null || data.getCustomerB() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerA and/or CustomerB object is required")
                    .build();
        }
        if (data.getCustomerA().getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerA ID is required").build();
        }
        if (data.getCustomerB().getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerB ID is required").build();
        }

        Route route = new Route();
        CustomerFacade customerFacade = new CustomerFacade();

        Customer customerA = customerFacade.find(data.getCustomerA().getId());
        if (customerA == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerA ID wasn't found").build();
        }

        Customer customerB = customerFacade.find(data.getCustomerB().getId());
        if (customerB == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("customerB ID wasn't found").build();
        }

        route.setCustomerA(customerA);
        route.setCustomerB(customerB);

        // Splits each element between a colon
        String[] parts = data.getDuration().split("\\:");
        System.out.println("parts[1] " + parts[1]);

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        int hoursInSeconds = hours * 3600;
        int minutesInSeconds = minutes * 60;

        String duration = String.format("%d:%02d:%02d", hoursInSeconds / 3600, (minutesInSeconds % 3600) / 60,
                (seconds % 60));
        route.setDuration(duration);
        route.setDistance(data.getDistance());

        Route newlyRoute = new Route();
        newlyRoute = routerFacade.create(route);
        return Response.status(Response.Status.CREATED).entity(newlyRoute).build();
    }

    @GET
    @Path("/shortest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShortestRoute() {
        List<Route> routes = routerFacade.findAll();
        List<Route> minRoutes = new ArrayList<Route>();
        List<Route> maxRoutes = new ArrayList<Route>();

        // for (int i = 0; i < minRoutes.size(); i++) {
        // System.out.println("i "+ i);
        // }

        // array to be sorted in, this array is necessary
        // when we sort object datatypes, if we don't, 
        // we can sort directly into the input array     
        int[] aux = new int[routes.size()];
        
        // find the smallest and the largest value
        minRoutes.add(routes.get(0));
        maxRoutes.add(routes.get(0));
        for (int i = 1; i < routes.size(); i++) {
            if (routes.get(i).getDistance() < minRoutes.get(0).getDistance()) {
                minRoutes.set(0, routes.get(i));
            } else if (routes.get(i).getDistance() > maxRoutes.get(0).getDistance()) {
                maxRoutes.set(0, routes.get(i));
            }
        }

        // init array of frequencies
        int[] counts = new int[maxRoutes.get(0).getDistance() - minRoutes.get(0).getDistance() + 1];

        // init the frequencies/elements on different indexes
        for (int i = 0; i < routes.size(); i++) {
            counts[routes.get(i).getDistance() - minRoutes.get(0).getDistance()]++;
        }

        // recalculate the array - create the array of occurences
        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
            System.out.println("counts i" + counts[i]);
        }

        /*
         * Sort the array right to the left 1) Look up in the array of
         * occurences the last occurence of the given value 2) Place it into the
         * sorted array 3) Decrement the index of the last occurence of the
         * given value 4) Continue with the previous value of the input array
         * (goto set1), terminate if all values were already sorted
         */
        for (int i = routes.size() - 1; i >= 0; i--) {
            System.out.println("distt "+ routes.get(i).getDistance());
            System.out.println("counts[routes.get(i).getDistance() - minRoutes.get(0).getDistance()] "+ counts[routes.get(i).getDistance() - minRoutes.get(0).getDistance()]);
            aux[counts[routes.get(i).getDistance() - minRoutes.get(0).getDistance()]--] = routes.get(i).getDistance();
        }
        
        return Response.status(Response.Status.CREATED).entity("worksa").build();
    }
}
