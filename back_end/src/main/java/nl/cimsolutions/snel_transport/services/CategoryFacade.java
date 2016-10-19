package nl.cimsolutions.snel_transport.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.cimsolutions.snel_transport.models.Category;

public class CategoryFacade extends AbstractFacade<Category> {
    EntityManagerFactory emf;
    
	public CategoryFacade() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }
}
