package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;

import nl.cimsolutions.snel_transport.models.Orders;
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
    
    public Route shortestDistance(Orders order, List<Orders> orders){
        Route route = new Route();
        if (order.getCustomer() != null) {
            route.setCustomerA(order.getCustomer());                    
        }
        
        //to do: create for loop 
        if(i+1 < orders.size()) {
            if (orders.get(i + 1).getCustomer() != null) {
                route.setCustomerB(orders.get(i + 1).getCustomer());                    
            }   
        }
        
    }
}
