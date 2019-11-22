package ut.microservices.investormicroservice.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractJpaDAO<T extends Serializable> {
 
   private Class< T > clazz;
  
   @PersistenceContext
   EntityManager entityManager;
  
   public void setClazz( Class<T> clazzToSet ) {
       this.clazz = clazzToSet;
   }
  
   public T findOne( Long id ){
     return entityManager.find( clazz, id );
   }
  
   public void save( T entity ){
     entityManager.persist( entity );
   }
  
    
   public void update( T entity ){
      entityManager.merge( entity );
   }
  
   public void delete( T entity ){
      entityManager.remove( entity );
   }

   public List<T> findBy(String column, String value){
     return entityManager.createQuery("from " + clazz.getName() + " a " + " where a." +column+"='"+value+"'").getResultList();
   } 
   public List<T> findBy(String column1,String value1,String column2,String value2){
     return entityManager.createQuery("from "+ clazz.getName()+" a  where a."+column1+"='"+value1+"' and a."+column2+"='"+value2+"'").getResultList();
   }
   public List<T> findAll(){
     return entityManager.createQuery("from "+ clazz.getName()+" a").getResultList();
   }
 }