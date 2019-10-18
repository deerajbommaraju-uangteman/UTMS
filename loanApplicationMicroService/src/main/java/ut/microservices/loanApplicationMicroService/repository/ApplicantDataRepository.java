package ut.microservices.loanApplicationMicroService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ut.microservices.loanApplicationMicroService.model.ApplicantData;



@Component
public interface ApplicantDataRepository extends JpaRepository<ApplicantData,Long>{

    @Query(value = "SELECT c FROM ApplicantData c where c.EmailAddress=:email")
    public List<ApplicantData> findByEmail(String email);

}