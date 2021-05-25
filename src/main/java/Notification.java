import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.Response.Status.OK;

public class Notification {

    private static Logger logger = Logger.getLogger(Notification.class.getName());
    private static String chatId;
    private static String URL;
    private static Configuration configuration = null;
    private static Map<String, Message> sentMessages = new HashMap<>();

    private static void initialize(){
        configuration = new ClientConfig().connectorProvider(new ApacheConnectorProvider());
        final ConfigData configData = ConfigData.getInstance();
        URL = "https://api.telegram.org/bot" + configData.getBotApiToken() + "/sendMessage";
        chatId = configData.getChatId();
    }

    public static void send(String correlationId, String msg) {
        logger.info("sending message with Id " + correlationId);

        if(configuration == null){
            initialize();
        }

        Message message = Message.from( chatId, msg );

        if(isDuplicate(correlationId, message)) {
            return;
        }

        Client client = ClientBuilder.newClient(configuration)
                                     .register(JacksonFeature.class);
        try {
            final Response response = client.target(URL)
                                            .request(MediaType.APPLICATION_JSON)
                                            .buildPost(Entity.json(message))
                                            .invoke();
            if(response.getStatus() != OK.getStatusCode())
                logger.log(Level.WARNING, "Error sending message " + response.getStatusInfo().getReasonPhrase());
            else {
                logger.info("Message successfully sent");
                addToSentMessages(correlationId, message);
            }
        }catch(Exception ex){
            logger.log(SEVERE, "Exception in sending message", ex);
        } finally {
            client.close();
        }

    }

    private static void addToSentMessages(String id, Message value) {
        sentMessages.put(id, value);
    }

    private static boolean isDuplicate(String id, Message message){
        Message oldMessage;
        if(!sentMessages.containsKey(id))
            return false;

        oldMessage = sentMessages.get(id);
        Instant oneHourSinceLastMessage = oldMessage.timeStamp.plus(Duration.ofHours( 1));

        if(message.timeStamp.isAfter( oneHourSinceLastMessage))
            return false;

        return oldMessage.text.equals(message.text);
    }
}
