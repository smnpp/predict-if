package modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.Consultation;
import modele.ProfilAstral;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T01:24:49")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> motDePasse;
    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile SingularAttribute<Client, String> genre;
    public static volatile SingularAttribute<Client, ProfilAstral> profilAstral;
    public static volatile SingularAttribute<Client, Boolean> enConsultation;
    public static volatile SingularAttribute<Client, String> tel;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> adressePostale;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile ListAttribute<Client, Consultation> consultations;

}