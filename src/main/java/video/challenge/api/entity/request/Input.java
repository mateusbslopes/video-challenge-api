package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;
import video.challenge.api.exception.VideoChallengeException;
import video.challenge.api.util.PropertyManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Represents the bitmovin Input request.
 */
public class Input extends BitMovin {

    private final PropertyManager propertyManager;

    public Input(String name) throws IOException, VideoChallengeException {
        super("/encoding/inputs/s3");

        propertyManager = PropertyManager.getInstance();

        JSONObject params = new JSONObject();
        params.put("name", "sample.mkv");
        params.put("cloudRegion", "SA_EAST_1");
        params.put("bucketName", "video-challenge-api");
        params.put("accessKey", propertyManager.getProperty("amazon.accessKey"));
        params.put("secretKey", propertyManager.getProperty("amazon.secretKey"));

        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }


}
