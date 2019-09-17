package video.challenge.api.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import video.challenge.api.entity.request.*;
import video.challenge.api.exception.VideoChallengeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Class used to encode the videos.
 */
public class Encoding {

    protected static String inputId;
    protected static String outputId;
    protected static String h624VideoConfigurationId;
    protected static String encodeId;
    protected static String streamId;
    protected static HttpClient httpClient;

    /**
     * Create all bitmovin configuration and sent it to be encoded.
     */
    public static void encode() throws VideoChallengeException {
        try {
            httpClient = HttpClientBuilder.create().build();
            createInput();
            createOutput();
            createH624VideoConfiguration();
            createEncode();
            createStream();
            createMuxing();
            startEncoding();
        } catch (IOException | ParseException e) {
            throw new VideoChallengeException("An error occur trying to encode the video: " + e);
        }
    }

    /**
     * Creates the muxing configuration on bitmovin server.
     * @throws IOException Thrown when occur an error trying to execute the request.
     */
    private static void createMuxing() throws IOException, VideoChallengeException {
        Muxing request = new Muxing(encodeId, streamId);
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        System.out.println(responseStr);
    }

    /**
     * Sent request to start the encoding.
     * @throws IOException Thrown when occur an error trying to execute the request.
     */
    private static void startEncoding() throws IOException, VideoChallengeException {
        StartEncode request = new StartEncode(encodeId);
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        convert(is, Charset.defaultCharset());
    }

    /**
     * Creates the stream on bitmovin server.
     * @throws IOException Thrown when occur an error trying to execute the request.
     * @throws ParseException Thrown when occur an error trying to parse the response.
     */
    private static void createStream() throws IOException, ParseException, VideoChallengeException {
        Stream request = new Stream(encodeId, inputId, outputId, h624VideoConfigurationId);
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject)responseJson.get("data");
        JSONObject result = (JSONObject)data.get("result");
        streamId = (String)result.get("id");
    }

    /**
     * Creates the encode on bitmovin server.
     * @throws IOException Thrown when occur an error trying to execute the request.
     * @throws ParseException Thrown when occur an error trying to parse the response.
     */
    private static void createEncode() throws IOException, ParseException, VideoChallengeException {
        Encode request = new Encode();
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject)responseJson.get("data");
        JSONObject result = (JSONObject)data.get("result");
        encodeId = (String)result.get("id");
    }

    /**
     * Creates the h264 video configuration on bitmovin server.
     * @throws IOException Thrown when occur an error trying to execute the request.
     * @throws ParseException Thrown when occur an error trying to parse the response.
     */
    private static void createH624VideoConfiguration() throws IOException, ParseException, VideoChallengeException {
        H264VideoConfiguration request = new H264VideoConfiguration();
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject)responseJson.get("data");
        JSONObject result = (JSONObject)data.get("result");
        h624VideoConfigurationId = (String)result.get("id");
    }

    /**
     * Creates the input on the bitmovin server.
     * @throws IOException Thrown when occur an error trying to execute the request.
     * @throws ParseException Thrown when occur an error trying to parse the response.
     */
    private static void createInput() throws IOException, ParseException, VideoChallengeException {
        Input request = new Input("test1");
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject)responseJson.get("data");
        JSONObject result = (JSONObject)data.get("result");
        inputId = (String)result.get("id");
    }

    /**
     * Creates the output on bitmovin server.
     * @throws IOException Thrown when occur an error trying to execute the request.
     * @throws ParseException Thrown when occur an error trying to parse the response.
     */
    private static void createOutput() throws IOException, ParseException, VideoChallengeException {
        Output request = new Output();
        HttpResponse response = httpClient.execute(request);
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject) responseJson.get("data");
        JSONObject result = (JSONObject) data.get("result");
        outputId = (String)result.get("id");
    }

    /**
     * Convert the input stream to a string.
     * @param inputStream InputStream to be converted.
     * @param charset Charset to be used.
     * @return String with the content of the inputStream.
     */
    public static String convert(InputStream inputStream, Charset charset) {
        try (Scanner scanner = new Scanner(inputStream, charset.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

}
