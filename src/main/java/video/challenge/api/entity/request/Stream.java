package video.challenge.api.entity.request;

import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;
import video.challenge.api.exception.VideoChallengeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Represents the bitmovin Stream request.
 */
public class Stream extends BitMovin {

    public Stream(String encodeId, String inputId, String outputId, String codecConfigId) throws IOException, VideoChallengeException {
        super("/encoding/encodings/" + encodeId + "/streams");

        JSONObject params = new JSONObject();

        JSONArray inputStreams = new JSONArray();
        JSONObject inputStream = new JSONObject();
        inputStream.put("inputId", inputId);
        inputStream.put("inputPath", "/");
        inputStream.put("selectionMode", "VIDEO_RELATIVE");
        inputStreams.add(inputStream);
        params.put("inputStreams", inputStreams);

        JSONArray outputStreams = new JSONArray();
        JSONObject outputStream = new JSONObject();
        outputStream.put("outputId", outputId);
        outputStream.put("outputPath", "0efaa94a-b3f6-468b-8415-0ffc21904a49/" + System.currentTimeMillis());
        outputStreams.add(outputStream);
        params.put("outputs", outputStreams);

        params.put("codecConfigId", codecConfigId);
        String a = params.toJSONString();
        StringEntity entity = new StringEntity(params.toJSONString());
        setEntity(entity);
    }
}
