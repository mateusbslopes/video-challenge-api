package video.challenge.api.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import video.challenge.api.entity.BitMovin;
import video.challenge.api.entity.request.Input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Enconding {

    public static void encode(String name){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            BitMovin request = new Input("test1");
            HttpResponse response = httpClient.execute(request);

            HttpEntity ent = response.getEntity();
            InputStream is = ent.getContent();
            String responseStr = convert(is, Charset.defaultCharset());
            JSONParser parser = new JSONParser();
            JSONObject inputResponseJson = (JSONObject) parser.parse(responseStr);
            JSONObject data = (JSONObject)inputResponseJson.get("data");
            JSONObject result = (JSONObject)data.get("result");
            String id = (String)result.get("id");



            System.out.println(id);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static String convert(InputStream inputStream, Charset charset) throws IOException {
        try (Scanner scanner = new Scanner(inputStream, charset.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

}
