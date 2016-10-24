package nl.cimsolutions.snel_transport.controllers;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Truck;
import nl.cimsolutions.snel_transport.services.OrderListFacade;
import nl.cimsolutions.snel_transport.services.TruckFacade;

@Path("orderlist")
public class OrderListController {

	OrderListFacade oFacade = new OrderListFacade();
	@GET
	//@PrePersist
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getAllorderlists() {
		
		List<OrderList> orderListIds =oFacade.AssignTrucksToOrders();
		
		return oFacade.generateOrderList(orderListIds);
	}

}