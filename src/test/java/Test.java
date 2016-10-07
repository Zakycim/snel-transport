import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

    public static void main(String[] args) {
       System.out.println("fadfsa");
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
//       EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport-test");
       

       System.out.println(emf);
    }

}
