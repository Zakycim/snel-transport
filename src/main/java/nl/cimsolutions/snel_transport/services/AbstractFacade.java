package nl.cimsolutions.snel_transport.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.cimsolutions.snel_transport.models.OrderList;
import nl.cimsolutions.snel_transport.models.Product;


public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    EntityManagerFactory entityManagerFactory;

    protected abstract EntityManager getEntityManager();
  
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    public void setup(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("snel-transport");
        setEntityManagerFactory(this.entityManagerFactory);
    }
    
    public T create(T entity) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(entity);
        em.flush();
        tx.commit();
        em.close();
        getEntityManagerFactory().close();
       
        return entity;
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }


    public List<T> findAll(String jpqlQuery) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("snel-transport");
    	EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createQuery(jpqlQuery);
		List<T> result = query.getResultList();
        em.flush();
        tx.commit();
        em.close();

        return result;

    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void emptyTable() {
        EntityTransaction tx = getEntityManager().getTransaction();
        tx.begin();
        Query q = getEntityManager().createNativeQuery("DELETE FROM public.\"User\" ");
        q.executeUpdate();
        tx.commit();
    }
    
    public AbstractFacade(Class<T> entityClass) {
        setup();
        this.entityClass = entityClass;
    }

}
