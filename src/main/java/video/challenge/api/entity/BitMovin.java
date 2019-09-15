package video.challenge.api.entity;

import org.apache.http.client.methods.HttpPost;

public class BitMovin extends HttpPost {

    public BitMovin(){
        super("https://api.bitmovin.com/v1/encoding/inputs/s3");
        addHeader("X-Api-Key", "");
        addHeader("Content-Type", "application/json");
    }

}
