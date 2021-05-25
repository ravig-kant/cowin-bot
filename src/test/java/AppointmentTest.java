import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentTest {

    private static String expectedMesage = "Appointment available at My center for age 18 to 44 on 24-12-2008. " +
                                            "Number of available doses of Covaxin are [dose1 : 200] and [dose2 : 100].\n" +
                                            "Register at : https://selfregistration.cowin.gov.in/";

    @Test
    public void message() {
        Appointment.Builder builder = new Appointment.Builder();

        final Appointment appointment = builder.name("My center")
                                                .date("24-12-2008")
                                                .minAge(18)
                                                .numberOfDoses1(200)
                                                .numberOfDoses2(100)
                                                .slot("10:00 - 11:00")
                                                .vaccine("Covaxin")
                                                .build();
        assertEquals(expectedMesage, appointment.message());
    }
}