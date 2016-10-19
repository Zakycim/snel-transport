package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import nl.cimsolutions.snel_transport.models.Order;
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

    public List findWithName(String name) {
        return getEntityManager().createQuery(
            "SELECT u FROM User u WHERE u.name = :name ")
            .setParameter("name", name)
            .getResultList();
    }
}