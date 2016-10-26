package nl.cimsolutions.snel_transport.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import nl.cimsolutions.snel_transport.controllers.ProductController;
import nl.cimsolutions.snel_transport.models.Product;

public class ProductFacade extends AbstractFacade<Product> {
    EntityManagerFactory emf;
    
    @PersistenceContext(unitName = "snel-transport")
    private EntityManager em;


    public ProductFacade() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//  public List<Product> GetAllProducts() {
//
//      return findAll();
//  }
    
    public Product getProduct(Integer id) {

        return  find(id);
    }
    public List<Product> getAllProducts() {
        return findAll("SELECT p FROM Product p ORDER BY p.id");
    }
}
