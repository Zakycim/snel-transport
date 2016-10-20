package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Truck;

public class OrderListFacade extends AbstractFacade<OrderList> {

    @PersistenceContext(unitName = "snel-transport")
    private EntityManager em;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    EntityManager testEm = emf.createEntityManager();
    
    public OrderListFacade() {
        super(OrderList.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<OrderList> findWithOrderId(long truckId) {
        
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
      em = emf.createEntityManager();
      
      EntityTransaction tx = em.getTransaction();
      tx.begin();
      
      em.flush();
      tx.commit();
      
      Query query = em.createQuery("SELECT o FROM OrderList o WHERE o.truckId = :truckId");
      query.setParameter("truckId", truckId);
      List resultList = query.getResultList();
      
      em.close();
      emf.close();
        
        return resultList;
    }
public Long getCountTrucks() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
	       em = emf.createEntityManager();
	      
	      EntityTransaction tx = em.getTransaction();
	      tx.begin();
	      
	      em.flush();
	      tx.commit();
		Query query = em.createQuery("SELECT COUNT(t) FROM OrderList t");
		long orderlistCount = (Long) query.getSingleResult();
		em.close();
	      emf.close();
		return orderlistCount;
	}

    
    public List<OrderList> AssignTrucksToOrders(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    	em = emf.createEntityManager();
    	 
    	TruckFacade tf = new TruckFacade();
    	//OrdersFacade of = new OrdersFacade();
    	
    	Long countTrucks = tf.getCountTrucks();
    	int availableTime = 480;
    	
//    	Truck truck = new Truck();
    	//List<Orders> orders = of.getAllOrders();
    	
    	OrderList ol = new OrderList();
    	
    	OrderList test = em.find(OrderList.class, 1L);
    	Long id = 1L;//ol.getId();
    	Long idorderlist = 1L;
    	List<OrderList> list = new ArrayList<>();
    	List<OrderList> orderListbla = findAll();
    	int counter = orderListbla.size() + 1;
    	for(int i = 0; i< countTrucks; i++){
//    		idTruck = truck.getId();
    		availableTime = 480;
    		do{
    			test = em.find(OrderList.class, idorderlist);
    	    	em.getTransaction().begin();
    			//ol.setTruckId(id);
    	    	
    			
//    			ol.setOrder(orders);
//    			list.add(ol);
    			System.out.println("availabletime:" +availableTime );
    			System.out.println("i:" +i +"counter:"+ counter + "counttrucks:"+countTrucks.intValue() );
    	    	  test.setTruckId(id);
    	    	  //em.refresh(test);
    	    	  em.getTransaction().commit();
    	    	  idorderlist ++;
    	    	  if(idorderlist >= counter) {i = countTrucks.intValue();break;}//= idorderlist ;
    	    	  availableTime-= 60;
      			if(availableTime <0) break;
    		}while(availableTime>0);// || idorderlist >=3);
    		id++;
    		list.add(test);
    	}
    	    	
    	return list;
    }
}