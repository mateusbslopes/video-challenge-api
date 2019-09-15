package video.challenge.api.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import video.challenge.api.entity.request.Encode;
import video.challenge.api.entity.request.H264VideoConfiguration;
import video.challenge.api.entity.request.Input;
import video.challenge.api.entity.request.Output;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Encoding {

    protected static String inputId;
    protected static String outputId;
    protected static String h624VideoConfigurationId;
    protected static String encodeId;
    protected static HttpClient httpClient;

    public static void encode(String name){
        try {
            httpClient = HttpClientBuilder.create().build();

//            setInputId();
//            setOutputId();
//            setH624VideoConfigurationId();
            setEncodeId();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void setEncodeId() throws IOException, ParseException {
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

    private static void setH624VideoConfigurationId() throws IOException, ParseException {
        // Do input request
        H264VideoConfiguration request = new H264VideoConfiguration();
        HttpResponse response = httpClient.execute(request);
        // Get input response
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject)responseJson.get("data");
        JSONObject result = (JSONObject)data.get("result");
        h624VideoConfigurationId = (String)result.get("id");
    }

    private static void setInputId() throws IOException, ParseException {
        // Do input request
        Input request = new Input("test1");
        HttpResponse response = httpClient.execute(request);
        // Get input response
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject)responseJson.get("data");
        JSONObject result = (JSONObject)data.get("result");
        inputId = (String)result.get("id");
    }

    private static void setOutputId() throws IOException, ParseException {
        // Do output request
        Output request = new Output();
        HttpResponse response = httpClient.execute(request);
        // Get output response
        HttpEntity ent = response.getEntity();
        InputStream is = ent.getContent();
        String responseStr = convert(is, Charset.defaultCharset());
        JSONParser parser = new JSONParser();
        JSONObject responseJson = (JSONObject) parser.parse(responseStr);
        JSONObject data = (JSONObject) responseJson.get("data");
        JSONObject result = (JSONObject) data.get("result");
        outputId = (String)result.get("id");
    }

    public static String convert(InputStream inputStream, Charset charset) throws IOException {
        try (Scanner scanner = new Scanner(inputStream, charset.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

}
