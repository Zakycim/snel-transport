package nl.cimsolutions.snel_transport.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.cimsolutions.snel_transport.models.Category;
import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.services.CategoryFacade;
import nl.cimsolutions.snel_transport.services.CustomerFacade;

@Path("categories")
public class CategoryController {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory() {
        
        CategoryFacade categoryFacade = new CategoryFacade();
        List<Category> category = categoryFacade.findAll();
        
        return category;
    }
}
