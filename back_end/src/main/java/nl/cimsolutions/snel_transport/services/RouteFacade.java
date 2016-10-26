package nl.cimsolutions.snel_transport.services;

import javax.persistence.EntityManager;
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
}
