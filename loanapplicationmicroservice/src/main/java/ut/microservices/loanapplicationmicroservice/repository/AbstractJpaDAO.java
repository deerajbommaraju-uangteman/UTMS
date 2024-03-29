package ut.microservices.loanapplicationmicroservice.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractJpaDAO<T extends Serializable> {
   private Class<T> clazz;
 
   @PersistenceContext
   EntityManager entityManager;

   SessionFactory sessionFactory;
 
   public void setClazz(Class< T > clazzToSet){
      this.clazz = clazzToSet;
   }
 
   public T findOne(int ID){
       System.out.println(ID);
     return (T) getCurrentSession().get(clazz, ID);
   }

   public List findAll() {
       return getCurrentSession().createQuery("from " + clazz.getName()).list();
   }

   @Transactional
   public T update(T entity) {
       return (T) getCurrentSession().merge(entity);
   }

   @Transactional
   public void updateOne(T entity) {
       getCurrentSession().update(entity);
   }

   public void delete(T entity) {
       getCurrentSession().delete(entity);
   }

   public void deleteById(long entityId) {
       T entity = findOne((int)(entityId));
       delete(entity);
   }
   public List<T> findValueByColumn(String column, String value){
       //System.out.println(clazz.getName()+"::"+column+"::"+value+"::");
    return getCurrentSession().createQuery("from "+clazz.getName()+" a where a."+column+"='"+value+"'").getResultList();
   }
   
    public T findValueByColumnSingleEntity(String column, String value){
        //System.out.println(clazz.getName()+"::"+column+"::"+value+"::");
        return (T) getCurrentSession().createQuery("from " + clazz.getName() + " a where a." + column + "='" + value + "'").getSingleResult();
    }

    public List<T> findValueByColumnOrder(String column, String value,String sortedColumn, String orderType){
        //System.out.println(clazz.getName()+"::"+column+"::"+value+"::");
        return getCurrentSession().createQuery("from "+clazz.getName()+" a where a."+column+"='"+value+"' order by"+sortedColumn+" "+orderType).getResultList();
    }
   @Transactional
   public Serializable save(T entity){
      return getCurrentSession().save(entity);
   }

   protected Session getCurrentSession() {
      return entityManager.unwrap(Session.class);//return sessionFactory.getCurrentSession();
   }

}