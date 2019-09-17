package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;
import video.challenge.api.exception.VideoChallengeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Represents the bitmovin request to start encoding.
 */
public class StartEncode extends BitMovin {

    public StartEncode(String encodeId) throws IOException, VideoChallengeException {
        super("/encoding/encodings/" + encodeId + "/start");
        JSONObject params = new JSONObject();
        params.put("encodingMode", "STANDARD");
        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }
}
