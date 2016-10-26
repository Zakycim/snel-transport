package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.cimsolutions.snel_transport.models.Status;
import nl.cimsolutions.snel_transport.models.Truck;

public class StatusFacade extends AbstractFacade<Status> {
    EntityManagerFactory emf;
    
    public StatusFacade() {
        super(Status.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Status> getAllStatus() {

        return findAll("SELECT t FROM Status t");
    }
}
