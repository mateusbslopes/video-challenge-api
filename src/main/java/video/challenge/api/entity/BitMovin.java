package video.challenge.api.entity;

import org.apache.http.client.methods.HttpPost;
import video.challenge.api.exception.VideoChallengeException;
import video.challenge.api.util.PropertyManager;

import java.io.IOException;

/**
 * Represents the basic configuration to bitmovin requests.
 */
public class BitMovin extends HttpPost {

    private PropertyManager propertyManager;

    public BitMovin(String apiRoute) throws IOException, VideoChallengeException {
        super("https://api.bitmovin.com/v1" + apiRoute);

        propertyManager = PropertyManager.getInstance();

        addHeader("X-Api-Key", propertyManager.getProperty("bitmovin.apiKey"));
        addHeader("Content-Type", "application/json");
    }

}
