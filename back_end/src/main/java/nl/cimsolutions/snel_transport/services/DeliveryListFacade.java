package nl.cimsolutions.snel_transport.services;

import javax.persistence.EntityManager;

import nl.cimsolutions.snel_transport.models.DeliveryList;
public class DeliveryListFacade extends AbstractFacade<DeliveryList>{

    public DeliveryListFacade() {
        super(DeliveryList.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }
}
