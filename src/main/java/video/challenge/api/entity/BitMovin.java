package video.challenge.api.entity;

import org.apache.http.client.methods.HttpPost;

public class BitMovin extends HttpPost {

    public BitMovin(String apiRoute){
        super("https://api.bitmovin.com/v1" + apiRoute);
        addHeader("X-Api-Key", "");
        addHeader("Content-Type", "application/json");
    }

}
