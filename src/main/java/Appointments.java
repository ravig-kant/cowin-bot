import java.util.Arrays;
import java.util.stream.Stream;

public class Appointments {
    public Center[] centers = new Center[0];

    public Stream<Appointment> filteredByAvailability(){
        return Arrays.stream(centers)
              .filter( x -> Arrays.stream(x.sessions).anyMatch( y -> y.available_capacity>0))
              .flatMap(this::toAppointments);
    }

    private Stream<Appointment> toAppointments(Center center) {
        return Arrays.stream(center.sessions)
                .filter(x -> x.available_capacity > 0)
                .map( y -> toAppointment(center, y));
    }

    private Appointment toAppointment(Center center, Session session) {
        Appointment.Builder builder = new Appointment.Builder();
        return builder.name(center.name)
                .date(session.date)
                .vaccine(session.vaccine)
                .slot(session.slots[0])
                .numberOfDoses1(session.available_capacity_dose1)
                .numberOfDoses2(session.available_capacity_dose2)
                .minAge(session.min_age_limit)
                .build();
    }

    public String toString(){
        return (centers.length >0 ) ? Arrays.toString(centers) : "[]";
    }
}
