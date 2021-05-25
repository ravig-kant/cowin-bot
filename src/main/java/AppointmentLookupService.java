import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.OK;

public class AppointmentLookupService {

    private static Logger logger = Logger.getLogger(AppointmentLookupService.class.getName());

    private static String cowinURL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict";
    private Configuration configuration;


    public AppointmentLookupService() {
        configuration = new ClientConfig().connectorProvider(new ApacheConnectorProvider());
    }

    public Appointments findByDistrict(int districtId){
        Date date = new Date();
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        final String formattedDate = simpleDateFormatter.format(date);
        Client client = ClientBuilder.newClient(configuration);
        try {
            WebTarget webTarget = client.target(cowinURL);
            final Response response = webTarget.queryParam("district_id", districtId)
                                                .queryParam("date", formattedDate).request()
                                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36 Edg/90.0.818.51")
                                                .buildGet()
                                                .invoke();
            if (response.getStatus() == OK.getStatusCode()) {
                final String entity = response.readEntity(String.class);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    final Appointments appointments = mapper.readValue(entity, Appointments.class);
                    logger.info("Total number of sessions " + appointments);
                    return appointments;
                } catch (IOException e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }
            }

            logger.info("Response Code is : " + response.getStatus());
        }catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            client.close();
        }
        return new Appointments();
    }
}
