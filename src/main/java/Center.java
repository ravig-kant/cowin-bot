import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonIgnoreProperties({"long",
        "state_name", "name_l", "state_name_l", "district_name", "district_name_l", "block_name",
        "block_name_l", "pindcode", "lat", "pincode", "vaccine_fees"})
public class Center {

    public String center_id;
    public String name;
    public String address;
    public String from;
    public String to;
    public String fee_type;
    public Session[] sessions;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Slots available at " + name);

        String availableSessions =
                Arrays.stream(sessions)
                        .filter(x -> x.available_capacity > 0)
                        .map(y -> new StringBuilder().append(" on " + y.date)
                                                     .append(" - " + y.slots[0])
                                                     .append(" for age " + y.min_age_limit)
                                                     .append(" vaccine : " + y.vaccine)
                                                     .append(". Available slots [dose 1] : " + y.available_capacity_dose1)
                                                     .append(". Available slots [dose 2] : " + y.available_capacity_dose2)
                                                     .toString())
                        .collect(Collectors.joining("\n"));

        if(availableSessions.length() == 0){
            availableSessions = " : 0";
        }
        return sb.append(availableSessions).toString();
    }

}
