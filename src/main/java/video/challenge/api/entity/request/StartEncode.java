package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;

import java.io.UnsupportedEncodingException;

public class StartEncode extends BitMovin {

    public StartEncode(String encodeId) throws UnsupportedEncodingException {
        super("/encoding/encodings/" + encodeId + "/start");
        JSONObject params = new JSONObject();
        params.put("encodingMode", "STANDARD");
        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }
}
