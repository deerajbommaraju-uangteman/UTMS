package ut.microservices.notificationMicroService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ut.microservices.notificationMicroService.model.MrTemplate;


@Component
public interface MrTemplateRepository extends JpaRepository<MrTemplate, Long> {

    @Query(value = "SELECT mt FROM MrTemplate mt where mt.MrtemplateID= :id")
    public List<MrTemplate> findById(Integer id );

}