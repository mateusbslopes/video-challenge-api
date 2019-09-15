package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;

import java.io.UnsupportedEncodingException;

public class H264VideoConfiguration extends BitMovin {

    public H264VideoConfiguration() throws UnsupportedEncodingException {
        super("/encoding/configurations/video/h264");

        JSONObject params = new JSONObject();

        params.put("name", "testH264VideoConfiguration");
        params.put("profile", "HIGH");
        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }

}
