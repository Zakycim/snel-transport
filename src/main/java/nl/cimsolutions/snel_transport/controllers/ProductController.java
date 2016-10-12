package nl.cimsolutions.snel_transport.controllers;

import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.services.ProductFacade;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */


	@GET
	public List<Product> getProducts() {
		ProductFacade pf = new ProductFacade();
		return pf.GetAllProducts();

	}

//	@POST
//	public Response CreateProduct(Product product) {
//        
//		ProductFacade pf = new ProductFacade();
//		 //Product newlyProduct = new Product(product.getProductID(),product.getName(),product.getCode(), product.getPrice(), product.getCategoryID());
//
//		JsonObject obj = Json.createObjectBuilder().add("message", "Your order has been created.").build();
//		return Response.status(Response.Status.CREATED).entity(pf.create(product)).build();
//	}


}
