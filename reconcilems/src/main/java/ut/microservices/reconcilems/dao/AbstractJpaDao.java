package ut.microservices.reconcilems.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractJpaDao< T extends Serializable > {
 
   private Class< T > clazz;
  
   @PersistenceContext
   EntityManager entityManager;
 
   public void setClazz( Class<T> clazzToSet ) {
      this.clazz = clazzToSet;
   }
 
   public void save( T entity ){
     entityManager.persist( entity );
   }
 
   public void delete( T entity ){
      entityManager.remove( entity );
   }

   public int findCountByColumn(String Column,String Value){
      return entityManager.createQuery("from "+clazz.getName()+" a where a."+Column+" like '%"+Value+"%'").getResultList().size();
   }

   public List<T> findByColumn(String Column,String Value){
      return entityManager.createQuery("from "+clazz.getName()+" a where a."+Column+"='"+Value+"'").getResultList();
   }

   public List<T> findByTwoColumns(String Column1,String Value1, String Column2,String Value2){
      return entityManager.createQuery("from "+clazz.getName()+" a where a."+Column1+"='"+Value1+"%'"+" and a."+Column2+"='"+Value2+"'").getResultList();
   }

   public List<T> findLatestRecord( ){
      return entityManager.createQuery( "from " + clazz.getName() + " a " + " order by a.id desc").setMaxResults(1).getResultList();
   }

   public int countSettlementID(String settlementId){
      return entityManager.createQuery("select count(a) from RekapPayment a where a.SettlementID like %"+settlementId+"%").getMaxResults();
   }

//    public List<T> findAll(String VaTransactionMerchantID){
//       return entityManager.createQuery("from "+clazz.getName()+" a where a."+Column+"='"+Value+"'").getResultList();
//    }
}