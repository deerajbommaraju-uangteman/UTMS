package ut.microservices.repaymentmicroservice.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractJpaDAO<T extends Serializable> {

    @PersistenceContext
    EntityManager entityManager;

    private Class< T > clazz;
  
    public void setClazz( Class<T> clazzToSet ) {
       this.clazz = clazzToSet;
    }

    public List< T > findAll(){
      return entityManager.createQuery( "from " + clazz.getName()).getResultList();
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

    public List<T> findByApplicantId(String ApplicantID) {
       
        return  entityManager.createQuery( "from " + clazz.getName() + " e " + " where " + " e.ApplicantID='"+ ApplicantID + "'").getResultList(); // + "and " +"e.IsVaActive =:IsVaActive"+ "and " +"e.status =:status"
        // q.setParameter("ApplicantID", ApplicantID);
        // q.setParameter("IsVaActive", "Y");
        // q.setParameter("status", 0);
        // return q.getResultList();
    
     }

     public List<T> findByVANumber(String VaNumber) {
        System.out.println(VaNumber);
        return  entityManager.createQuery( "from " + clazz.getName() + " e " + " where " + " e.VaNumber='"+ VaNumber + "'" + "and "+ "e.Status = '0'" + "and " +"e.IsVaActive= 'Y' order by e.ID desc").getResultList(); 
        // q.setParameter("today", date);
     }

     public List<T> findActiveVAByApplicantID(String applicantID) {
        return  entityManager.createQuery( "from " + clazz.getName() + " e " + " where " + " e.ApplicantID='"+ applicantID + "'" + "and "+ "e.Status = '0'" + "and " +"e.IsVaActive= 'Y' order by e.ID desc").getResultList(); 
      }

     public List<T> findVAInLogs(String VaNumber) {
        return  entityManager.createQuery( "from " + clazz.getName() + " e " + " where " + " e.VaNumber='"+ VaNumber + "'").getResultList(); 

     }

   //   public List<T> findByLoanId(String LoanApplicationID) {
   //    TypedQuery<ApplicationData> q = em.createQuery("SELECT a FROM " + clazz.getName() + " a JOIN "+clazz.getName()+" b ON a.id = i.order.id WHERE i.id = :itemId", PurchaseOrder.class);
   //    q.setParameter("itemId", item2.getId());
   //    q.getSingleResult();
   //      return  entityManager.createQuery( "from " + clazz.getName() + " e " + " where " + " e.LoanApplicationID='"+ LoanApplicationID + "'").getResultList(); 
   //   }

     public List<T> findValueByColumn(String column, String value){
         System.out.println(clazz.getName()+"::"+column+"::"+value+"::");
         return entityManager.createQuery("from "+clazz.getName()+" a where a."+column+"='"+value+"'").getResultList();
     }

     public List<T> findByTwoColumns(String column1, String value1, String column2, String value2){
      return entityManager.createQuery("from "+ clazz.getName()+" a  where a."+column1+"='"+value1+"' and a."+column2+"='"+value2+"'" +"order by 1 DESC").getResultList();
    }

     public List<T> findInstallmentRepayment(String value){
      return entityManager.createQuery("from "+clazz.getName()+" a where a.CustomerLoanRepaymentID ='"+value+"'"+ "and a.VtransStatus = 'P' and a.Status ='D' LIMIT 1").setMaxResults(1).getResultList();
     }
  
   //   public List<T> findByJoin(){
   //      return entityManager.createQuery("FROM "+clazz.getName()+" a LEFT JOIN ApplicantData b on a.ApplicationApplicantID = b.ApplicantID").getResultList();
   //   }
     
}