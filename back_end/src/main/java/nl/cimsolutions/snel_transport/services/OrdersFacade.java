package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Orders;
import nl.cimsolutions.snel_transport.models.Product;


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

//        public List findWithName(String name) {
//            return getEntityManager().createQuery(
//                "SELECT u FROM User u WHERE u.name = :name ")
//                .setParameter("name", name)
//                .getResultList();
//        }
        
        public List<Orders> getAllOrders() {

            return findAll("SELECT p FROM Orders p");
        }
        

        public List<Long> getOrderById(List<Orders> orders ){
            List<Long> listOfId = new ArrayList<>();
            for(Orders id : orders){
                listOfId.add(id.getId());
            }
            return listOfId;
        }
        
        
    }