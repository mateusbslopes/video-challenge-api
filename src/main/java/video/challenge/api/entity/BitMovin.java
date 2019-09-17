package video.challenge.api.entity;

import org.apache.http.client.methods.HttpPost;

/**
 * Represents the basic configuration to bitmovin requests.
 */
public class BitMovin extends HttpPost {

    public BitMovin(String apiRoute){
        super("https://api.bitmovin.com/v1" + apiRoute);
        addHeader("X-Api-Key", "");
        addHeader("Content-Type", "application/json");
    }

}
