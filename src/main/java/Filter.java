import java.util.Arrays;
import java.util.stream.Collectors;

public class Filter {

    public static Center[] onAvailability(Center[] centers){
        return Arrays.stream(centers)
                .filter(x -> Arrays.stream(x.sessions)
                                    .anyMatch( y -> y.available_capacity > 0))
                .collect(Collectors.toList())
                .toArray(new Center[0]);
    }

}
