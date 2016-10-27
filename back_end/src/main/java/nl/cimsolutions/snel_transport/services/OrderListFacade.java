package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.internal.jpa.deployment.JavaSECMPInitializer.TempEntityLoader;
import org.junit.Before;

import nl.cimsolutions.snel_transport.controllers.OrderListController;
import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Truck;

//@EntityListeners({OrderList.class,OrderListFacade.class,Orders.class,OrderListController.class})
public class OrderListFacade extends AbstractFacade<OrderList> {

    @PersistenceContext(unitName = "snel-transport")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    //private List<OrderList> orderList = findAll();
    private List<OrderList> orderLists = findAll("Select o FROM OrderList o");
    
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


    /**
     * This method returns the current date-1 and sets the time to 22:00. A
     * customer can place an order and receive it the next day until 22:00
     * 
     * @return
     */

    @SuppressWarnings("deprecation")
    public Date getLatestDeliveryTimeAndDate() {
        int numberOfDaysToLookBack = 1;
        Date date = new Date();
        date.setHours(22);
        date.setMinutes(00);
        date.setSeconds(00);
        date.setDate(date.getDate() - numberOfDaysToLookBack);
        System.out.println(date);
        return date;
    }

    /**
     * This method compares the date returned in getLatestDeliveryTimeAndDate()
     * with the dates in the database
     * 
     * @return
     */
    public List<Orders> getOrdersByDates() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Date date = getLatestDeliveryTimeAndDate();
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.status = '1' AND o.orderDate < :date");
        query.setParameter("date", date);
        List<Orders> orderDates = query.getResultList();
        System.out.println("Dates from database: " + orderDates);
        return orderDates;
    }
//    public List getAllCustomerIdandOrderId() {
//      System.out.println("dude");
//      return findAll("SELECT c FROM Orders c");
//    }
    
    public void AssignOrdersTolist() {
        Orders order = new Orders();
        OrderList temporalList1 = new OrderList();// = em.find(OrderList.class, 1L);
        int idCounter = 1;
        
        OrdersFacade of = new OrdersFacade();
        List<Orders> orders = getOrdersByDates();
        List<Long> idorders = of.getOrderById(orders);
        System.out.println("id orders: " + idorders.toString());
        List<OrderList> orderList = new ArrayList<>();
        Long orderID = 0L;
        int orderListSize = 0;
       EntityTransaction tx = em.getTransaction();
//List<Long> o = new ArrayList<>();
        for(int i=0; i< idorders.size(); i++){
            orderListSize = (findall().size()) +1 ;
            //System.out.println("hier stop ik"+ of.find(idorders.get(i)) + "size:" + idorders.size());
            System.out.println("order id "+ of.find(idorders.get(i))+ "size :" + orderListSize);
            //if(idCounter>orderListSize) break;
           // order = (Orders) o;
            orderID =idorders.get(i);
//            tx.begin();
          //  Query query = em.createQuery("INSERT OrderList o VALUES (orderID, '', 2L)" );
           // query.setParameter("orderID", orderID);
            //temporalList1.setId((long) orderListSize);
//            temporalList1 = em.find(OrderList.class, new Long (orderListSize+1));
           
           temporalList1.setOrder(of.find(orderID));
           temporalList1.setOrderOfOrder(1L);
           temporalList1.setTruckId(2L);
          // temporalList1.setId((long) i);
           System.out.println("orderlist: " + temporalList1.toString());
//           em.persist(temporalList1);
//           tx.commit();
           create(temporalList1); 
           // tx.begin();
            
           // em.persist(temporalList1);
           // tx.commit();
           // orderList.add(temporalList1);
           // idCounter++;
        }
        //em.close();
        
    }
    
//@PrePersist
   // @SuppressWarnings("rawtypes")
    

    

    /**
     * This method assigns the available trucks to orders using a list of orders
     * named OrderList The trucks are chosen by ID (after searching the ID's
     * from the truck table in the database)
     * 
     * @return: the list of type OrderList where the orders and trucks are
     *          divided
     */
//@PostPersist
    public List<OrderList> AssignTrucksToOrders() throws Exception {
        
      
        TruckFacade tf = new TruckFacade();
        OrdersFacade of = new OrdersFacade();

        Long countTrucks = tf.getCountTrucks();
        List<Long> truckIds = tf.getAllIdTrucks();

        OrderList ol = new OrderList();

        OrderList temporalList1 = em.find(OrderList.class, 1L);
        List<Long> listorderlist = getAllOrderIds();
//        List<OrderList> orderLists = findAll();

       // List<Orders> ordersForOrderList = getOrdersByDates();

        int lastOrderlistId = 0;
        int availableTime = 480;
        int orderListSize = orderLists.size();
        int idCounter = 0;
        EntityTransaction tx = em.getTransaction();
        
       // AssignOrdersTolist(getOrdersByDates());
        
        for (Long id : truckIds) {
            Long idOrderList = 0L;
            if ((orderListSize > 0) && (lastOrderlistId <= idCounter)) {
                Long idTruck = id;
                System.out.println("idtrucks:" + id + "////" + idTruck);
                availableTime = 480;
                for (idCounter = 1; idCounter <= orderListSize; idCounter++) {
                    if ((availableTime > 0) && (idCounter >= lastOrderlistId)) {
                       
                        availableTime -= 60;
                        System.out.println("time:" + availableTime);
                        try{
                            temporalList1 = em.find(OrderList.class, new Long(idCounter));
                        //OrderList temporalList2 = em.find(OrderList.class, new Long(idCounter));
                           System.out.println("counter orderlist: " + orderListSize);
                        temporalList1.setTruckId(idTruck);
//                        create(temporalList1); 
                        tx.begin();
                        lastOrderlistId = idCounter+1;
                        em.persist(temporalList1);
                        tx.commit();
                       
                        System.out.println("list: " + temporalList1);
                        }
                        catch(EntityNotFoundException ex){
                            
                        }
                    }
                }
            }
        }
        em.close();
        return (List<OrderList>) temporalList1;
    }

    public Response generateOrderList(List<OrderList> list) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(list);
        tx.commit();
        em.close();
        return Response.status(Response.Status.ACCEPTED).build();
    }

}