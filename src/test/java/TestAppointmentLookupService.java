import org.junit.Test;

public class TestAppointmentLookupService {
    @Test
    public void testApiClient(){

        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
        AppointmentLookupService appointmentLookupService = new AppointmentLookupService();
        appointmentLookupService.findByDistrict(194);
    }
}
