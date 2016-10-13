package nl.cimsolutions.snel_transport.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.services.OrderFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;

@Path("products")
public class ProductController {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
		ProductFacade pf = new ProductFacade();
		return pf.GetAllProducts();
	}

	 
}