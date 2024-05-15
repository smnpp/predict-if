package modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.Consultation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-15T22:33:12")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> motDePasse;
    public static volatile SingularAttribute<Employe, String> mail;
    public static volatile SingularAttribute<Employe, Date> dateNaissance;
    public static volatile SingularAttribute<Employe, Boolean> disponibilite;
    public static volatile SingularAttribute<Employe, Boolean> genre;
    public static volatile SingularAttribute<Employe, String> tel;
    public static volatile SingularAttribute<Employe, Long> id;
    public static volatile SingularAttribute<Employe, String> adressePostale;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile ListAttribute<Employe, Consultation> consultations;
    public static volatile SingularAttribute<Employe, Integer> nombreConsultations;

}