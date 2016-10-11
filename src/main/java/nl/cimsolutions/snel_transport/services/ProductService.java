package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import nl.cimsolutions.snel_transport.models.Product;



public class ProductService {

private Product products = new Product();
	
	public ProductService(){
		 String dbName = "snel-transport";
	        
//	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
//	        EntityManager em = emf.createEntityManager();
//	        
//	        EntityTransaction tx = em.getTransaction();
//	        tx.begin();
//	        em.persist(product);
//	        em.flush();
//	        tx.commit();
//	        em.close();
//	        emf.close();
	        
	}
	
	public List<Product> getAllProducts(){

		
		return new ArrayList<Product>(products.values());
	
	}
}
