package nl.cimsolutions.snel_transport.services;

import java.nio.ReadOnlyBufferException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.Route;

public class RouteFacade extends AbstractFacade<Route> {

    public RouteFacade() {
        // TODO Auto-generated constructor stub
        super(Route.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public void clearTable(){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        EntityManager em = emf.createEntityManager();
        
        try {

            em.getTransaction().begin();

            Query q1 = em.createNativeQuery("DELETE FROM Route");

            q1.executeUpdate();

            em.getTransaction().commit();
        } catch (ReadOnlyBufferException e) {
            e.printStackTrace();
        }
    }
}
