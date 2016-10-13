package nl.cimsolutions.snel_transport.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Product;

public class OrderListFacade extends AbstractFacade<OrderList>{
	
	@PersistenceContext(unitName = "snel-transport")
	private EntityManager em;

	public OrderListFacade() {
		super(OrderList.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

//	public List<Product> GetAllProducts() {
//
//		return findAll();
//	}
	
	public OrderList GetOrderList(Integer id) {

		return  find(id);
	}
	
	public OrderList FillOrderList() {
		 OrderList orderlist = new OrderList();
		 OrderFacade orderFacade = new OrderFacade();
		 orderlist.setOrder(orderFacade.findAll());
		return orderlist;
	}
	
	
	
}
