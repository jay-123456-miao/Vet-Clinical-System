package Model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-02T18:21:01")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, String> vet_username;
    public static volatile SingularAttribute<Appointment, LocalDateTime> appointment_time;
    public static volatile SingularAttribute<Appointment, String> gender;
    public static volatile SingularAttribute<Appointment, String> diagnosis;
    public static volatile SingularAttribute<Appointment, String> customer_username;
    public static volatile SingularAttribute<Appointment, String> prognosis;
    public static volatile SingularAttribute<Appointment, Long> contact_number;
    public static volatile SingularAttribute<Appointment, String> species;
    public static volatile SingularAttribute<Appointment, Long> id;
    public static volatile SingularAttribute<Appointment, String> pet_username;
    public static volatile SingularAttribute<Appointment, String> email;
    public static volatile SingularAttribute<Appointment, Integer> age;
    public static volatile SingularAttribute<Appointment, String> status;

}