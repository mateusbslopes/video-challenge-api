package video.challenge.api.util;

import video.challenge.api.exception.VideoChallengeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private Properties properties;
    private static PropertyManager propertyManager;

    private PropertyManager(){}

    public static PropertyManager getInstance() throws VideoChallengeException {
        try {
            if(propertyManager == null){
                InputStream inputStream = new FileInputStream("application.properties");
                Properties props = new Properties();
                props.load(inputStream);

                propertyManager = new PropertyManager();
                propertyManager.setProperties(props);
            }
            return propertyManager;
        } catch (IOException e) {
            throw new VideoChallengeException("Could not load properties.");
        }

    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
