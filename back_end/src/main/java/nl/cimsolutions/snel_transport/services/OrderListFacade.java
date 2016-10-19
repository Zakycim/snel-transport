package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.OrderList;

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
}