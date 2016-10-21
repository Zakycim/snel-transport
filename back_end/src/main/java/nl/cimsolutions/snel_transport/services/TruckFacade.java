package nl.cimsolutions.snel_transport.services;

import java.sql.Array;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
		Truck truck = new Truck();
		
//System.out.println("idtruck:"+ getTrukId() );
		return findAll("SELECT t FROM Truck t");
		
	}

	public Long getCountTrucks() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
	       em = emf.createEntityManager();
//	      
//	      EntityTransaction tx = em.getTransaction();
//	      tx.begin();
//	      
//	      em.flush();
//	      tx.commit();
		Query query = em.createQuery("SELECT COUNT(t) FROM Truck t");
		long truckCount = (Long) query.getSingleResult();
		return truckCount;
	}
	
//	public List<Long> getTrukId(){
//		List<Truck> listtrucks = getAllTrucks();
//		Array[] array = (Array[]) listtrucks.toArray();
//		array.
//	}
	
	 public List<Long> getAllIdTrucks() {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
	       em = emf.createEntityManager();
	       Query query = em.createQuery("SELECT t.id FROM Truck t");
	       List<Long> truckIds =  (List<Long>) query.getResultList();
	       return truckIds;
	 }
	 

}
