package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.cimsolutions.snel_transport.models.Order;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Product;

public class OrderListFacade{
	
	public OrderListFacade(Class<OrderLine> entityClass) {
		//super(entityClass);
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "snel-transport")
	private EntityManager em;


//	public OrderListFacade() {
//		super(OrderList.class);
//	}

//	@Override
//	protected EntityManager getEntityManager() {
//		return em;
//	}
	
//	public String getStatus(OrderList orderlist){
//		
//	   Order order;
//	   order = em.find(Order.class, orderlist.getOrderListID());
//	   return order.getStatus();
//	}
	

//	public List<OrderList> GetAllOrderLists() {
//
//		//return findAll("SELECT ol FROM OrderList ol");
//	}
}
