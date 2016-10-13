package nl.cimsolutions.snel_transport.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Product;
import nl.cimsolutions.snel_transport.services.OrderFacade;
import nl.cimsolutions.snel_transport.services.OrderListFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;

@Path("orderlist")
public class OrderListController {

	
//    public List<OrderList> getOrderLists() {
//		ProductFacade pf = new ProductFacade();
//		return pf.findAll();
//	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public OrderList GenerateOrderList(){
    	//OrderListFacade orderlistFacade = new OrderListFacade();
    	
    			return GenerateOrderList();
        //

        //return orderlists;
    }
    
}
