import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.cimsolutions.snel_transport.models.OrderLine;
import nl.cimsolutions.snel_transport.models.OrderList;

public class Test {

    public static void main(String[] args) {
//       System.out.println("fadfsa");
//       EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
////       EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport-test");
//       
//
//       System.out.println(emf);
    	System.out.println(OrderList.class.getSimpleName());
    }

}