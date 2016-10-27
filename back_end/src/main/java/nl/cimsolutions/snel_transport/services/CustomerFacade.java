package nl.cimsolutions.snel_transport.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import nl.cimsolutions.snel_transport.models.Customer;
import nl.cimsolutions.snel_transport.models.Order;

public class CustomerFacade extends AbstractFacade<Customer> {
    EntityManagerFactory emf;
    
    public CustomerFacade() {
        super(Customer.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    protected EntityManagerFactory getEntityManagerFactory() {
//        if (System.getenv("Environment") == null) {
//            return this.emf = Persistence.createEntityManagerFactory("snel-transport");
//        }
//        switch (System.getenv("Environment")) {
//        case "TEST":
//            return this.emf = Persistence.createEntityManagerFactory("snel-transport-test");
//        default:
//            return this.emf = Persistence.createEntityManagerFactory("snel-transport");
//        }
//    }
    
}
