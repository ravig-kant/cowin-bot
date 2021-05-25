import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class AppointmentsTest {

    private static String centersInJson
            = "{\"centers\":[{\"center_id\":613121,\"name\":\"Inderpuri UPHC XIN\",\"address\":\"Inderpuri Sirsa\"," +
            "\"state_name\":\"Haryana\",\"district_name\":\"Sirsa\",\"block_name\":\"Sirsa\",\"pincode\":125055," +
            "\"lat\":29,\"long\":74,\"from\":\"10:00:00\",\"to\":\"14:00:00\",\"fee_type\":\"Free\"," +
            "\"sessions\":[{\"session_id\":\"e5634505-dfd6-44c7-9835-f30a7658e010\",\"date\":\"23-05-2021\"," +
            "\"available_capacity\":2,\"min_age_limit\":45,\"vaccine\":\"COVAXIN\",\"slots\":[\"10:00AM-11:00AM\"," +
            "\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-02:00PM\"],\"available_capacity_dose1\":0," +
            "\"available_capacity_dose2\":2}]}," +
            "{\"center_id\":578499,\"name\":\"Inderpuri UPHC LD\"," +
            "\"address\":\"Inderpuri Sirsa\",\"state_name\":\"Haryana\",\"district_name\":\"Sirsa\"," +
            "\"block_name\":\"Sirsa\",\"pincode\":125055,\"lat\":29,\"long\":74,\"from\":\"09:00:00\"," +
            "\"to\":\"15:00:00\",\"fee_type\":\"Free\"," +
            "\"sessions\":[{\"session_id\":\"9a681956-e194-44f7-95f3-e1c951589e46\",\"date\":\"23-05-2021\"," +
            "\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"09:00AM-10:00AM\"," +
            "\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-03:00PM\"],\"available_capacity_dose1\":0," +
            "\"available_capacity_dose2\":0}]}," +
            "{\"center_id\":90422,\"name\":\"Baragudha CHC LD\",\"address\":\"Near " +
            "Bus Stand\",\"state_name\":\"Haryana\",\"district_name\":\"Sirsa\",\"block_name\":\"Baragudha\"," +
            "\"pincode\":125078,\"lat\":29,\"long\":74,\"from\":\"10:00:00\",\"to\":\"15:00:00\"," +
            "\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"b65b3921-1f26-41fc-aef3-97fd87cb5442\"," +
            "\"date\":\"23-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\"," +
            "\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-03:00PM\"]," +
            "\"available_capacity_dose1\":0,\"available_capacity_dose2\":0}]},{\"center_id\":90345,\"name\":\"Odhan " +
            "CHC LD\",\"address\":\"Odhan CHC Kalanwali Road\",\"state_name\":\"Haryana\"," +
            "\"district_name\":\"Sirsa\",\"block_name\":\"Odhan\",\"pincode\":125077,\"lat\":29,\"long\":74," +
            "\"from\":\"10:00:00\",\"to\":\"15:00:00\",\"fee_type\":\"Free\"," +
            "\"sessions\":[{\"session_id\":\"d154c835-2f88-4534-b85a-032bb4faad21\",\"date\":\"23-05-2021\"," +
            "\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\"," +
            "\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-03:00PM\"],\"available_capacity_dose1\":0," +
            "\"available_capacity_dose2\":0}]},{\"center_id\":85716,\"name\":\"Kalanwali CHC LD\",\"address\":\"Mandi" +
            " Kalanwali\",\"state_name\":\"Haryana\",\"district_name\":\"Sirsa\",\"block_name\":\"Kalanwali\"," +
            "\"pincode\":125201,\"lat\":29,\"long\":74,\"from\":\"10:00:00\",\"to\":\"15:00:00\"," +
            "\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"d2f6694d-9f78-4407-a0d4-b09e661b77c9\"," +
            "\"date\":\"23-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\"," +
            "\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-03:00PM\"]," +
            "\"available_capacity_dose1\":0,\"available_capacity_dose2\":0}]}]}";
    @Test
    public void get() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final Appointments appointments = mapper.readValue(centersInJson, Appointments.class);
        final List<Appointment> availableAppointments = appointments.filteredByAvailability().collect(Collectors.toList());

        assertTrue( availableAppointments.size() > 0);
    }
}