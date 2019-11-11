package ut.microservices.investorMicroService.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractJpaDao<T extends Serializable> {
 
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
   public List<T> findVANumberByInvestorID(Integer investorID){
     return entityManager.createQuery("from "+ clazz.getName()+" a  where a.investorID='"+investorID+"' and a.status=0").getResultList();
   }
 }