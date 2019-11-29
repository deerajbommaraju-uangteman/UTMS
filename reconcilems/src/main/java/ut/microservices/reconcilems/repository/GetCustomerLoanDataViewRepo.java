package ut.microservices.reconcilems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ut.microservices.reconcilems.models.GetCustomerLoanDataView;

@Component
@Repository
public interface GetCustomerLoanDataViewRepo extends CrudRepository<GetCustomerLoanDataView, Long> {
	

	@Query(value = "SELECT * from RCNMS_VW_GetCustomerLoanData", nativeQuery = true)
    public List<GetCustomerLoanDataView> findAll();
        
	
	
}
    
