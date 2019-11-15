package ut.microservices.reconcileMicroService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ut.microservices.reconcileMicroService.Models.RekapReconcilePaymentviewModel;

@Component
@Repository
public interface RekapReconcilePaymentviewRepo extends CrudRepository<RekapReconcilePaymentviewModel, Long> {
	

	@Query(value = "SELECT * from RCNMS_VW_RekapReconcilePayment", nativeQuery = true)
    public List<RekapReconcilePaymentviewModel> findAll();
        
	
	
}
    
