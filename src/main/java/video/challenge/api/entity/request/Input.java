package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;

import java.io.UnsupportedEncodingException;

public class Input extends BitMovin {

    public Input(String name) throws UnsupportedEncodingException {
        super("/encoding/inputs/s3");
        JSONObject params = new JSONObject();

        params.put("name", name);
        params.put("cloudRegion", "SA_EAST_1");
        params.put("bucketName", "video-challenge-api");
        params.put("accessKey", "");
        params.put("secretKey", "");

        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }


}
