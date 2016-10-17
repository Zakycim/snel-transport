package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.OrderLine;

public class OrderLineFacade extends AbstractFacade<OrderLine> {

    @PersistenceContext(unitName = "snel-transport")
    private EntityManager em;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    EntityManager testEm = emf.createEntityManager();
    
    public OrderLineFacade() {
        super(OrderLine.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<OrderLine> findWithOrderId(long orderId) {
        
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
      EntityManager em = emf.createEntityManager();
      
      EntityTransaction tx = em.getTransaction();
      tx.begin();
      
      em.flush();
      tx.commit();
      
      Query query = em.createQuery("SELECT o FROM OrderLine o WHERE o.orderId = :orderId");
      query.setParameter("orderId", orderId);
      List resultList = query.getResultList();
      
      em.close();
      emf.close();
        
        return resultList;
    }
}