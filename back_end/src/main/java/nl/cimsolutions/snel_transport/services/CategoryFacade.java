package nl.cimsolutions.snel_transport.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import nl.cimsolutions.snel_transport.models.Category;

public class CategoryFacade extends AbstractFacade<Category> {
    
	public CategoryFacade() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected EntityManagerFactory getEntityManagerFactory(Category entity) {
        // TODO Auto-generated method stub
        return null;
    }
}
