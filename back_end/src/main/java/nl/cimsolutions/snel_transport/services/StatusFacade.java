package nl.cimsolutions.snel_transport.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.cimsolutions.snel_transport.models.Status;

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

    @Override
    protected EntityManagerFactory getEntityManagerFactory(Status status) {
        if (status.getEnv() == null) {
            return this.emf = Persistence.createEntityManagerFactory("snel-transport");
        }

        switch (status.getEnv()) {
        case "TEST":
            return this.emf = Persistence.createEntityManagerFactory("snel-transport-test");
        default:
            return this.emf = Persistence.createEntityManagerFactory("snel-transport");
        }
    }
}
