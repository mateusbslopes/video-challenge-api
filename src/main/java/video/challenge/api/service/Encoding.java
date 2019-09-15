package video.challenge.api.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import video.challenge.api.entity.request.*;

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
    protected static String streamId;
    protected static HttpClient httpClient;

    public static void encode(String name){
        try {
            httpClient = HttpClientBuilder.create().build();

            encodeId = "1d2c9203-8ece-4ae0-b6a5-88d8748dbdff";
            inputId = "209cff35-5c6f-4541-98ac-3ca08fbdf846";
            outputId = "2ece7963-a61e-4683-81de-246cb25ee258";
            h624VideoConfigurationId = "0a4449f4-4ae7-429c-ba22-e3f942c8f30c";
            streamId = "c7f43adb-f95e-480d-857c-6eaee5fc3825";

            setInputId();
            setOutputId();
            setH624VideoConfigurationId();
            setEncodeId();
            setStreamId();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void setStreamId() throws IOException, ParseException {
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
