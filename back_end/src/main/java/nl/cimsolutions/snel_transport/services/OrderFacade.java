package nl.cimsolutions.snel_transport.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nl.cimsolutions.snel_transport.models.Order;


public class OrderFacade extends AbstractFacade<Order> {
    

        @PersistenceContext(unitName = "snel-transport")
        private EntityManager em;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport-test");
        EntityManager testEm = emf.createEntityManager();
        
        public OrderFacade() {
            super(Order.class);
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