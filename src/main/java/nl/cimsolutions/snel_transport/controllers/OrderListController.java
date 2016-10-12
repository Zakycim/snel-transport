package nl.cimsolutions.snel_transport.controllers;

import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.services.OrderListFacade;

@Path("orderlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class OrderListController  {
	
	//@GET
	//public List<OrderList> getOrderLists() {
		//OrderListFacade pf = new OrderListFacade();
		//return pf.GetAllOrderLists();

//	}

	//@GET
	//@Path("/{productId}")
	//public List<OrderList> UpdateOrderList() {
		//Map<String,OrderList,String> orderlists = orderlists.put(Order.class.);
		
		//OrderListFacade of = new OrderListFacade();
		// return of.GetAllOrderLists();
//
//		orderlist.setOrderListID(orderlist.getOrderListID());
//			//return productService.updateProduct(product);
//	
//		JsonObject obj = Json.createObjectBuilder().add("message", "Your order has been created.").build();
//		return Response.status(Response.Status.CREATED).entity(pf.create(orderlist)).build();
		//return Response.ok(orderlists).build();
		
	//}

}
