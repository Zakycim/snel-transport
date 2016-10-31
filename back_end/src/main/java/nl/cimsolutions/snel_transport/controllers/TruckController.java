package nl.cimsolutions.snel_transport.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import nl.cimsolutions.snel_transport.models.Route;
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
	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Route> getTruckById(@PathParam("id") long id) {
        //Get shortest routes for a truck
        TruckFacade truckFacade = new TruckFacade();

        Truck truck = truckFacade.find(id);
        List<Route> routes = truck.getRoutes();
        // Check if truck is empty
        if (truck == null) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Couldn't find truck").build();
        } else {
//            return Response.status(Response.Status.OK).entity(truck).build();
        }
        
        List<Route> minRoutes = new ArrayList<Route>();
        List<Route> maxRoutes = new ArrayList<Route>();
        List<Route> sortedRoutes = new ArrayList<Route>();

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
        }

        HashMap hm = new HashMap();
        /*
         * Sort the array right to the left 1) Look up in the array of
         * occurences the last occurence of the given value 2) Place it into the
         * sorted array 3) Decrement the index of the last occurence of the
         * given value 4) Continue with the previous value of the input array
         * (goto set1), terminate if all values were already sorted
         */
        for (int i = routes.size() - 1; i >= 0; i--) {
            hm.put( (counts[routes.get(i).getDistance() - minRoutes.get(0).getDistance()]--), routes.get(i));
        }
        
        // Get a set of the entries
        Set set = hm.entrySet();
        
        // Get an iterator
        Iterator i = set.iterator();
        
        // Display elements
        while(i.hasNext()) {
           Map.Entry me = (Map.Entry)i.next();
           System.out.print("me key "+me.getKey() + ": ");
           System.out.println("me value "+ ((Route) me.getValue()).getDistance());
           sortedRoutes.add((Route) me.getValue());
        }
        
        return sortedRoutes;
    }
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTruck(Truck data) {
        Truck truck = new Truck();//(data.getId(), data.getLicensePlate(), true);
        truck.setLicensePlate("abcdefg");
        truck.setAvailable(true);
        TruckFacade truckFacade = new TruckFacade();
         
        return Response.status(Response.Status.CREATED).entity(truckFacade.create(truck)).build(); 
    }
}
