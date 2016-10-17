package nl.cimsolutions.snel_transport.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.services.StatusFacade;

@Path("statuses")
public class StatusController {

    public StatusController() {
        // TODO Auto-generated constructor stub
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStatus(Status data) {
        Status status = new Status();
        Status status1 = new Status();
        Status status2 = new Status();
        Status status3 = new Status();
        
        if(data.getStatus() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("A status is required").build();
        }
        
        status.setStatus("In behandeling");
        status1.setStatus("Verzonden");
        status2.setStatus("Afgeleverd");
        status3.setStatus("Geannuleerd");
        
        StatusFacade statusFacade = new StatusFacade();
        
        Status newlyStatus = new Status();
        newlyStatus = statusFacade.create(status);
        //you get an error somehow if you want to insert more statuses in DB..
        newlyStatus = statusFacade.create(status1);
        newlyStatus = statusFacade.create(status2);
        newlyStatus = statusFacade.create(status3);
        
        return Response.status(Response.Status.CREATED).entity(newlyStatus).build();
    }

}
