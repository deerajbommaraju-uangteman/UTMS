package ut.microservices.investorMicroService.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


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
   //  public List< T > findAll(){
   //     return entityManager.createQuery( "from " + clazz.getName() )
   //      .getResultList();
   //  }
  
    public void save( T entity ){
       System.out.println(entity);
       entityManager.persist( entity );
    }
  
    
    public void update( T entity ){
       entityManager.merge( entity );
    }
  
    public void delete( T entity ){
       entityManager.remove( entity );
    }

    public List<T> findBy(T entity){
       return null;
    }

   public List<T> findByLoanState(String state) {
      return entityManager.createQuery( "from " + clazz.getName() + " a " + " where " + " a.State='"+state+"'" ).getResultList();
  }
   public List<T> findByLoanAppID(String loanAppID) {
      return entityManager.createQuery( "from " + clazz.getName() + " a " + " where " + " a.loanAppID='"+ loanAppID+"'").getResultList();
   }
   public void updateLoanState(String loanAppID,String state) {
      String query="update "+ clazz.getName()+" a set a.State='"+state+"' where a.loanAppID='"+loanAppID+"'";
      System.out.println(query);
      entityManager.createNativeQuery(query).executeUpdate();
   }
   public List<T> findVANumberByInvestorID(Integer investorID){
      return entityManager.createQuery("from "+ clazz.getName()+" a  where a.investorID='"+investorID+"' and a.status=0").getResultList();
   }  

 

   //  public void deleteById( Long entityId ){
   //     T entity = getById( entityId );
   //     delete( entity );
   //  }
 }