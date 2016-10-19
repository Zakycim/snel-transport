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
import nl.cimsolutions.snel_transport.models.Orders;

public class CustomerFacade extends AbstractFacade<Customer> {
    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }
}
