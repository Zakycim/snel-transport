package nl.cimsolutions.snel_transport.services;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import nl.cimsolutions.snel_transport.models.OrderLine;
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

    /**
     * 
     * @param truckId
     * @return
     */
    public List<OrderList> findWithOrderId(long truckId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ol FROM OrderList ol WHERE ol.truckId = :truckId");
        query.setParameter("truckId", truckId);
        List resultList = query.getResultList();
        return resultList;
    }

    public Long getCountOrderList() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(ol) FROM OrderList ol");
        long orderlistCount = (Long) query.getSingleResult();
        return orderlistCount;
    }

    public List<Long> getAllOrderIds() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT ol.id FROM OrderList ol");
        List<Long> orderIds = (List<Long>) query.getResultList();
        return orderIds;
    }

    public List<OrderList> AssignTrucksToOrders() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();

        TruckFacade tf = new TruckFacade();

        Long countTrucks = tf.getCountTrucks();
        int availableTime = 480;
        List<Long> truckIds = tf.getAllIdTrucks();

        OrderList ol = new OrderList();

        OrderList test = em.find(OrderList.class, 1L);
        Long id = 1L;
        Long idorderlist = 0L;
        List<Long> list = getAllOrderIds();
        List<OrderList> orderListbla = findAll();
        Long lastOrderlistId;

        int counter = orderListbla.size();
        for (Long t : truckIds) {
            if (idorderlist >= counter) {
                break;
            } else {

                id = t;
                availableTime = 480;
                lastOrderlistId = idorderlist;
                for (Long o : list) {
                    idorderlist = o;
                    System.out.println(" o2: " + o);
                    if (idorderlist >= lastOrderlistId) {
                        System.out.println(" o: " + o);

                        if (availableTime <= 0)
                            break;
                        if (idorderlist > counter) {
                            break;
                        } else {
                            test = em.find(OrderList.class, idorderlist);
                            em.getTransaction().begin();
                            System.out.println("availabletime:" + availableTime);
                            System.out.println(
                                    "id:" + id + " counter: " + counter + " counttrucks: " + countTrucks.intValue());
                            test.setTruckId(id);
                            em.getTransaction().commit();
                            // idorderlist++;
                            availableTime -= 60;
                        }
                    }
                }
                orderListbla.add(test);
            }

        }

        return orderListbla;
    }

    /**
     * This method compares the date returned in getLatestDeliveryTimeAndDate()
     * with the dates in the database
     * 
     * @return
     */
    public List<Date> getOrderDates() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
        em = emf.createEntityManager();
        Date date = getLatestDeliveryTimeAndDate();
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.status = '1' AND o.orderDate < :date");
        query.setParameter("date", date);
        List<Date> orderDates = query.getResultList();
        System.out.println("Dates from database: " + orderDates);
        return orderDates;
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

}