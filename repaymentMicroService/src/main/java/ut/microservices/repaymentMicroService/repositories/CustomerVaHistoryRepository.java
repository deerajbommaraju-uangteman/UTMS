package ut.microservices.repaymentMicroService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ut.microservices.repaymentMicroService.models.CustomerVaHistory;

@Component
@Repository
public interface CustomerVaHistoryRepository extends JpaRepository<CustomerVaHistory,Long>{

    @Query(value = "SELECT c FROM CustomerVaHistory c where c.VaNumber=:va")
    public List<CustomerVaHistory> findByVaNumber(String va);

}