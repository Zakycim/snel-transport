package nl.cimsolutions.snel_transport.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import nl.cimsolutions.snel_transport.models.Orders;

public class OrdersFacade extends AbstractFacade<Orders> {

	@PersistenceContext(unitName = "snel-transport")
	private EntityManager em;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
	EntityManager testEm = emf.createEntityManager();

	public OrdersFacade() {
		super(Orders.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Orders> getAllOrders() {

		return findAll("SELECT p FROM Orders p");
	}
}