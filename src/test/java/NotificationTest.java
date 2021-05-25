import org.junit.Test;

import javax.ws.rs.core.Configuration;

import static org.junit.Assert.*;

public class NotificationTest {


    private static Configuration configuration = null;


    @Test
    public void send() {
        System.setProperty("vaccine.finder.chatid" , "-568561685");
        Notification.send("1", "For effective use of this channel, change the notification ring tone of this group. To change the ringtone, go to telegram settings -> Notification and Sounds -> Groups -> Add an Exception -> Sirsa Vaccine Notification -> Sound -> Choose a different ring tone & Change Smart Notification to higher frequency.\n" + "Slots are premium and get filled very quickly. So changing notification settings help in a swift action.");
    }
}