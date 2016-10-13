package nl.cimsolutions.snel_transport.controllers;

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
import nl.cimsolutions.snel_transport.services.CustomerFacade;

@Path("customers")
public class CustomerController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        CustomerFacade customerFacade = new CustomerFacade();
        List<Customer> customers = customerFacade.findAll();

        return customers;
    } 

}
