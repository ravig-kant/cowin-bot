import java.time.Instant;

public class Message {

    public String chat_id;
    public String text;
    public Instant timeStamp;

    public static Message from(String id, String text){
        Message message = new Message();
        message.chat_id = id;
        message.text = text;
        message.timeStamp = Instant.now();
        return message;
    }
}
