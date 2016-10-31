package nl.cimsolutions.snel_transport.services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Status;

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
	@SuppressWarnings("deprecation")
	public Date getLatestDeliveryTimeAndDate() {
		int numberOfDaysToLookBack = 0;
		Date date = new Date();
		date.setHours(15);
		date.setMinutes(30);
		date.setSeconds(00);
		date.setDate(date.getDate() - numberOfDaysToLookBack);
		System.out.println(date);
		return date;
	}

	/**
	 * This method compares the date returned in getLatestDeliveryTimeAndDate()
	 * with the dates in the database
	 * 
	 * @return
	 */
	public List<Orders> getOrdersByDates() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();
		Date date = getLatestDeliveryTimeAndDate();
		Status status = new Status();
		status.setId(1L);
		status.setName("In behandeling");
		Query query = em.createQuery("SELECT o FROM Orders o WHERE o.status = :STATUS AND o.orderDate < :date");
		query.setParameter("STATUS",status);
		query.setParameter("date", date);
		List<Orders> orderDates = query.getResultList();
		System.out.println("Dates from database: " + orderDates);
		return orderDates;
	}
	
}