package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;
import video.challenge.api.exception.VideoChallengeException;
import video.challenge.api.util.PropertyManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Represents the bitmovin Output request.
 */
public class Output extends BitMovin {

    private PropertyManager propertyManager;

    public Output() throws IOException, VideoChallengeException {
        super("/encoding/outputs/s3");

        propertyManager = PropertyManager.getInstance();

        JSONObject params = new JSONObject();
        params.put("name", "testOut");
        params.put("cloudRegion", "SA_EAST_1");
        params.put("bucketName", "video-challenge-api");
        params.put("accessKey", propertyManager.getProperty("amazon.accessKey"));
        params.put("secretKey", propertyManager.getProperty("amazon.secretKey"));

        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }

}
