package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Truck;

public class OrderListFacade extends AbstractFacade<OrderList> {

	@PersistenceContext(unitName = "snel-transport")
	private EntityManager em;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
	EntityManager testEm = emf.createEntityManager();

	public OrderListFacade() {
		super(OrderList.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<OrderList> findWithOrderId(long truckId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT o FROM OrderList o WHERE o.truckId = :truckId");
		query.setParameter("truckId", truckId);
		List resultList = query.getResultList();
		return resultList;
	}

	public Long getCountOrderList() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT COUNT(o) FROM OrderList o");
		long orderlistCount = (Long) query.getSingleResult();
		return orderlistCount;
	}

	public List<Long> getAllOrderIds() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT o.id FROM OrderList o");
		List<Long> orderIds = (List<Long>) query.getResultList();
		return orderIds;
	}

	public List<OrderList> AssignTrucksToOrders() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();

		TruckFacade tf = new TruckFacade();

		Long countTrucks = tf.getCountTrucks();
		int availableTime = 480;
		List<Long> truckIds = tf.getAllIdTrucks();

		OrderList ol = new OrderList();

		OrderList test = em.find(OrderList.class, 1L);
		Long id = 1L;
		Long idorderlist = 0L;
		List<Long> list = getAllOrderIds();
		List<OrderList> orderListbla = findAll();
		Long lastOrderlistId;

		int counter = orderListbla.size();
		for (Long t : truckIds) {
			if (idorderlist >= counter) {
				break;
			} else {

				id = t;
				availableTime = 480;
				lastOrderlistId = idorderlist;
				for (Long o : list) {
					idorderlist = o;
					System.out.println(" o2: " + o);
					if (idorderlist >= lastOrderlistId) {
						System.out.println(" o: " + o);
						
						if (availableTime <= 0)
							break;
						if (idorderlist > counter) {
							break;
						} else {
							test = em.find(OrderList.class, idorderlist);
							em.getTransaction().begin();
							System.out.println("availabletime:" + availableTime);
							System.out.println(
									"id:" + id + " counter: " + counter + " counttrucks: " + countTrucks.intValue());
							test.setTruckId(id);
							em.getTransaction().commit();
							// idorderlist++;
							availableTime -= 60;
						}
					}
				}
				orderListbla.add(test);
			}

		}

		return orderListbla;
	}

}