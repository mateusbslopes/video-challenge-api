package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;

import java.io.UnsupportedEncodingException;

/**
 * Represents the bitmovin Encode request.
 */
public class Encode extends BitMovin {

    public Encode() throws UnsupportedEncodingException {
        super("/encoding/encodings");
        JSONObject params = new JSONObject();

        params.put("name", "encodingTest");
        params.put("cloudRegion", "AWS_SA_EAST_1");

        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }

}
