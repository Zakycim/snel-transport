package nl.cimsolutions.snel_transport.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.internal.jpa.deployment.JavaSECMPInitializer.TempEntityLoader;

import nl.cimsolutions.snel_transport.controllers.OrderListController;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Truck;

//@EntityListeners({OrderList.class,OrderListFacade.class,Orders.class,OrderListController.class})
public class OrderListFacade extends AbstractFacade<OrderList> {

    @PersistenceContext(unitName = "snel-transport")
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    private EntityManager em = emf.createEntityManager();

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
        Query query = em.createQuery("SELECT o FROM OrderList o WHERE o.truckId = :truckId");
        query.setParameter("truckId", truckId);
        List resultList = query.getResultList();
        return resultList;
    }

    public Long getCountOrderList() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(o) FROM OrderList o");
        long orderlistCount = (Long) query.getSingleResult();
        return orderlistCount;
    }

    public List<Long> getAllOrderIds() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o.id FROM OrderList o");
        List<Long> orderIds = (List<Long>) query.getResultList();
        return orderIds;
    }

    // @PrePersist

    public List<OrderList> AssignTrucksToOrders() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
		TruckFacade tf = new TruckFacade();

		Long countTrucks = tf.getCountTrucks();
		int availableTime = 420;
		List<Long> truckIds = tf.getAllIdTrucks();
		OrderList ol = new OrderList();
		OrderList temporalList = em.find(OrderList.class, 1L);
		//em.getTransaction().begin();
		//Long id = 0L;
		//Long idorderlist = 0L;
		List<Long> listorderlist = getAllOrderIds();
		List<OrderList> orderListbla = findAll();
		Long lastOrderlistId= 0L;
		System.out.println("idtrucks:"+ truckIds);
		int counter = orderListbla.size();
		int j =0;
		 for(Iterator<Long> t = truckIds.iterator(); t.hasNext(); ) {
		     Long idOrderList = 0L;
		     if(counter>=0){
		       Long idTruck = t.next();
		       System.out.println("idtrucks:"+ truckIds);
		       availableTime = 480;
		       //lastOrderlistId = 0L;
		       //for(Iterator<Long> o = truckIds.iterator(); o.hasNext(); ) {
		       for(int i = 1 ; i<=counter; i++){
		           System.out.println("idorderlist:"+ i+ "////"+ listorderlist);
		         if(availableTime>0){
		            
		           //idOrderList = o.next();
		            System.out.println("idorderlist:"+ idOrderList);
		            if(i>j){//idOrderList > lastOrderlistId){
		              availableTime -=60;
		              System.out.println("time:"+ availableTime);
		              //counter--;
		              em.getTransaction().begin();
		              temporalList = em.find(OrderList.class,new Long(i));
		              
		              temporalList.setTruckId(idTruck);
		              lastOrderlistId= idOrderList + 1L;
		              j = i;
		              em.flush();
		              em.getTransaction().commit();
		              orderListbla.add(temporalList);
		              System.out.println("list: "+ temporalList);
		            }
		         }
		      }
		  }
		 }
		     
//		for (Long t : truckIds) {
//		    
//			if (idorderlist <= counter) {
//				id = t;
//				availableTime = 480;
//				lastOrderlistId = idorderlist;
//				for (Long o : list) {
//				    availableTime -= 60;
//				    if(availableTime > 0){
//					idorderlist = o;
//					System.out.println(" o2: " + o);
//					if (idorderlist >= lastOrderlistId) {
//						System.out.println(" o: " + o);
//						
//						if (idorderlist > counter) {
//						    System.out.println("idorderlist > counter");
//							break;
//						} else {
//						    //em.getTransaction().begin();
//							test = em.find(OrderList.class, lastOrderlistId);
//							
//							System.out.println("availabletime:" + availableTime);
//							System.out.println(
//									"id:" + id + " counter: " + counter + " counttrucks: " + countTrucks.intValue());
//							test.setTruckId(id);
//							//em.getTransaction().commit();
//							//idorderlist++;
//						}	
//						
//					}
//				}
//			}
//				orderListbla.add(test);
//			}
//
//		}
em.close();
		return orderListbla;
	}

    public Response generateOrderList(List<OrderList> list) {
        List<OrderList> orderlist;
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        em.close();
        return Response.status(Response.Status.ACCEPTED).build();
    }

}