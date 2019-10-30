package ut.microservices.investorMicroService.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "digisign_users")
@NamedQuery(name="DigisignUser.findAll", query="SELECT b FROM DigisignUser b")
@Data
public class DigisignUser implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="du_id")
    private int duId ;

    @Column(name="du_ap_id")
    private Long duApId ;

    @Column(name="du_email_user")
    private String duEmailUser;

    @Column(name="du_password")
    private String duPassword;

    @Column(name="du_status_registration")
    private String duStatusRegistration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="du_created_at")
    private Date duCreatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="du_updated_at")
    private Date duUpdatedAt;
}