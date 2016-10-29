package nl.cimsolutions.snel_transport.services;

import java.nio.ReadOnlyBufferException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.flush();
		tx.commit();

		Query query = em.createQuery("SELECT o FROM OrderList o WHERE o.truckId = :truckId");
		query.setParameter("truckId", truckId);
		List resultList = query.getResultList();

		em.close();
		emf.close();

		return resultList;
	}
	
	public List<Long> getAllOrderIds() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o.id FROM OrderList o");
        List<Long> orderIds = (List<Long>) query.getResultList();
        return orderIds;
    }
	
	public void clearTable(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		EntityManager em = emf.createEntityManager();
		
		try {

		    em.getTransaction().begin();

		    Query q1 = em.createNativeQuery("DELETE FROM OrderList");

		    q1.executeUpdate();

		    em.getTransaction().commit();
		} catch (ReadOnlyBufferException e) {
		    e.printStackTrace();
		}


	}

}