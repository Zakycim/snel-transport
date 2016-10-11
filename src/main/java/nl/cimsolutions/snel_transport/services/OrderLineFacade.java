package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import nl.cimsolutions.snel_transport.models.OrderLine;

public class OrderLineFacade extends AbstractFacade<OrderLine> {

    @PersistenceContext(unitName = "snel-transport")
    private EntityManager em;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport-test");
    EntityManager testEm = emf.createEntityManager();
    
    public OrderLineFacade() {
        super(OrderLine.class);
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