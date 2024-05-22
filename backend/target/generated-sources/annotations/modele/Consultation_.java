package modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.Client;
import modele.Employe;
import modele.Medium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T01:24:49")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> date;
    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, String> commentaire;
    public static volatile SingularAttribute<Consultation, Boolean> validation;

}