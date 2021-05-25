import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigData {
    private static Logger logger = Logger.getLogger(ConfigData.class.getName());
    private static ConfigData INSTANCE = null;
    private final Properties properties = new Properties();

    private ConfigData(){
        String propertyFile = (System.getProperty("vaccine.finder.config.file") != null) ?
                                System.getProperty("vaccine.finder.config.file") :
                                ClassLoader.getSystemResource("application.properties").getFile();
        try(final InputStream inputStream = Files.newInputStream(Paths.get(propertyFile))) {
            properties.load(inputStream);
            if( !properties.containsKey("district")
                    || !properties.containsKey("botApiToken")
                    || !properties.containsKey("chat"))
            {
                logger.severe("Bot Token, district id or chat id is missing in configuration file ");
                throw new IllegalStateException("Bot Token, district id or chat id is missing in configuration file");
            }

            //Ensure Integer value is proper. Any parisng exception will propagate
            Integer.parseInt(properties.getProperty("district"));
        } catch (IOException ex) {
            logger.severe("Could not read the configuration file " + ex.getMessage());
            throw new IllegalStateException("Could not read the configuration file ");
        }
    }

    public static ConfigData getInstance() throws IllegalStateException {
        if(INSTANCE == null){
            INSTANCE = new ConfigData();
        }

        return INSTANCE;
    }

    public String getChatId(){
        return properties.getProperty("chat");
    }

    public String getBotApiToken(){
        return properties.getProperty("botApiToken");
    }

    public int getDistrict(){
        return Integer.parseInt(properties.getProperty("district"));
    }
}
