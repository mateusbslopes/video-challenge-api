package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;
import video.challenge.api.exception.VideoChallengeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Represents the bitmovin H264 video configuration request.
 */
public class H264VideoConfiguration extends BitMovin {

    public H264VideoConfiguration() throws IOException, VideoChallengeException {
        super("/encoding/configurations/video/h264");

        JSONObject params = new JSONObject();

        params.put("name", "testH264VideoConfiguration");
        params.put("bitrate", 1500000L);
        params.put("width", 1024);
        params.put("profile", "HIGH");
        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }

}
