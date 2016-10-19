package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.cimsolutions.snel_transport.models.Truck;

public class TruckFacade extends AbstractFacade<Truck> {
	
	@PersistenceContext(unitName = "snel-transport")
    private EntityManager em;


    public TruckFacade() {
        super(Truck.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//  public List<Truck> getAllTrucks() {
//
//      return findAll();
//  }
//    
//    public Truck getTruck(Long id) {
//
//        return  find(id);
//    }
    public List<Truck> getAllTrucks() {

        return findAll("SELECT t FROM Truck t");
    }
//    
//    public List<Truck> getAllTruckPlates() {
//    	return findAll("SELECT licenseplate FROM Truck");
//    }
}
