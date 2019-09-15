package video.challenge.api.entity.request;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import video.challenge.api.entity.BitMovin;

public class Muxing extends BitMovin {

    public Muxing(String encodeId, String streamId) {
        super("/encoding/encodings/" + encodeId + "/muxings/fmp4");

        JSONObject params = new JSONObject();
        JSONArray streams = new JSONArray();
        JSONObject stream = new JSONObject();
        stream.put("streamId", streamId);
        streams.add(stream);
        params.put("streams", streams);
        params.put("segmentLength", 4D);
        params.put("segmentNaming", "seg_%number%.m4s");
        params.put("initSegmentName", "init.mp4");

    }
}
