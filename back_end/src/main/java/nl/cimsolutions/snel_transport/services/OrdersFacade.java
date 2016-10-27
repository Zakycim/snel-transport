package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.Orders;

public class OrdersFacade extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "snel-transport")
    private EntityManager em;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    EntityManager testEm = emf.createEntityManager();

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Orders> getAllOrders() {

        return findAll("SELECT p FROM Orders p");
    }

    public List<Long> getOrderById(List<Orders> orders) {
        List<Long> listOfId = new ArrayList<>();
        for (Orders id : orders) {
            listOfId.add(id.getId());
        }
        return listOfId;
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
}