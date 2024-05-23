package modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.Consultation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T22:19:45")
@StaticMetamodel(Medium.class)
public class Medium_ { 

    public static volatile SingularAttribute<Medium, String> presentation;
    public static volatile SingularAttribute<Medium, Boolean> genre;
    public static volatile SingularAttribute<Medium, Long> id;
    public static volatile ListAttribute<Medium, Consultation> consultations;
    public static volatile SingularAttribute<Medium, String> denomination;
    public static volatile SingularAttribute<Medium, Integer> nombreConsultations;

}