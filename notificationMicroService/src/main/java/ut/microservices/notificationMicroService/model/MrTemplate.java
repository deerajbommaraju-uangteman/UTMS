package ut.microservices.notificationMicroService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="mr_template")
@Data
public class MrTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mt_id")
    private Integer MrtemplateID;

    @Column(name="mt_name")
    private String MrTemplateName;

    
    @Column(name="mt_category")
    private String MrTemplateCategory;

 
    @Column(name="mt_desc_en")
    private String MrTemplateDescriptionEnglish;

    @Column(name="mt_desc_in")
    private String MrTemplateDescriptionIndonesian;

 
    @Column(name="mt_status")
    private String Status;

}