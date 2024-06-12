package Model;

import Model.Appointment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-02T18:21:01")
@StaticMetamodel(Vet.class)
public class Vet_ { 

    public static volatile SingularAttribute<Vet, String> expertise2;
    public static volatile SingularAttribute<Vet, Integer> password;
    public static volatile SingularAttribute<Vet, String> expertise1;
    public static volatile SingularAttribute<Vet, String> gender;
    public static volatile SingularAttribute<Vet, Long> contactnumber;
    public static volatile SingularAttribute<Vet, String> usertype;
    public static volatile SingularAttribute<Vet, Long> id;
    public static volatile ListAttribute<Vet, Appointment> appoinments;
    public static volatile SingularAttribute<Vet, String> email;
    public static volatile SingularAttribute<Vet, Integer> age;
    public static volatile SingularAttribute<Vet, String> username;
    public static volatile SingularAttribute<Vet, String> status;

}