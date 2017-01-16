package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.Truck;

public class TruckFacade extends AbstractFacade<Truck> {

	@PersistenceContext(unitName = "snel-transport")
	private EntityManager em;

	public TruckFacade() {
		super(Truck.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Long getCountTrucks() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();

		Query query = em.createQuery("SELECT COUNT(t) FROM Truck t");
		long truckCount = (Long) query.getSingleResult();
		return truckCount;
	}

	public List<Long> getAllIdTrucks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
		em = emf.createEntityManager();
		Query query = em.createQuery("SELECT t.id FROM Truck t WHERE t.Available = true");
		List<Long> truckIds = (List<Long>) query.getResultList();
		return truckIds;
	}

	// public List<Truck> getAllTrucks() {
	//
	// return findAll();
	// }
	//
	// public Truck getTruck(Long id) {
	//
	// return find(id);
	// }
	public List<Truck> getAllTrucks() {

		return findAll("SELECT t FROM Truck t");
	}
	//
	// public List<Truck> getAllTruckPlates() {
	// return findAll("SELECT licenseplate FROM Truck");
	// }
}
