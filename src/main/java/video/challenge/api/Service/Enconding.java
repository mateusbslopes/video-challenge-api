package video.challenge.api.Service;

import com.bitmovin.api.BitmovinApi;
import com.bitmovin.api.encoding.AclEntry;
import com.bitmovin.api.encoding.AclPermission;
import com.bitmovin.api.encoding.EncodingOutput;
import com.bitmovin.api.encoding.InputStream;
import com.bitmovin.api.encoding.codecConfigurations.H264VideoConfiguration;
import com.bitmovin.api.encoding.codecConfigurations.enums.ProfileH264;
import com.bitmovin.api.encoding.encodings.Encoding;
import com.bitmovin.api.encoding.encodings.muxing.FMP4Muxing;
import com.bitmovin.api.encoding.encodings.muxing.MuxingStream;
import com.bitmovin.api.encoding.encodings.streams.Stream;
import com.bitmovin.api.encoding.enums.CloudRegion;
import com.bitmovin.api.encoding.enums.StreamSelectionMode;
import com.bitmovin.api.encoding.inputs.S3Input;
import com.bitmovin.api.encoding.outputs.S3Output;
import com.bitmovin.api.exceptions.BitmovinApiException;
import com.bitmovin.api.http.RestException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Enconding {

    public static void encode(String name){
        try {
            // Set input
            BitmovinApi bitmovin = new BitmovinApi("");
            S3Input input = new S3Input();
            // @todo Remove when commit
            input.setId("test1");
            input.setAccessKey("");
            input.setSecretKey("");
            input.setBucketName("video-challenge-api");

            input = bitmovin.input.s3.create(input);

            // Set output
            S3Output output = new S3Output();
            output.setId("encodedTest");
            output.setAccessKey("");
            output.setSecretKey("");
            output.setBucketName("video-challenge-api");

            output = bitmovin.output.s3.create(output);
            String outputId = output.getId();


            // Set video codec config
            H264VideoConfiguration videoCodecConfig = new H264VideoConfiguration();

            videoCodecConfig.setName("Test Video");
            videoCodecConfig.setBitrate(1500000L);
            videoCodecConfig.setWidth(1024);
            videoCodecConfig.setProfile(ProfileH264.HIGH);

            videoCodecConfig = bitmovin.configuration.videoH264.create(videoCodecConfig);

            // Create encoding
            Encoding encoding = new Encoding();

            encoding.setName("Test Video");
            encoding.setCloudRegion(CloudRegion.AWS_SA_EAST_1);
            encoding.setEncoderVersion("2.22.0");

            encoding = bitmovin.encoding.create(encoding);

            // Create stream video
            String inputPath = "/";

            Stream streamVideo = new Stream();

            InputStream inputStreamVideo1 = new InputStream();
            inputStreamVideo1.setInputId(input.getId());
            inputStreamVideo1.setInputPath(inputPath);
            inputStreamVideo1.setSelectionMode(StreamSelectionMode.AUTO);

            streamVideo.setCodecConfigId(videoCodecConfig.getId());
            streamVideo.addInputStream(inputStreamVideo1);

            streamVideo = bitmovin.encoding.stream.addStream(encoding, streamVideo);

            AclEntry aclEntry = new AclEntry();
            aclEntry.setPermission(AclPermission.PUBLIC_READ);
            List<AclEntry> aclEntries = new ArrayList<AclEntry>();
            aclEntries.add(aclEntry);

            // Create muxing
            double segmentLength = 4D;
            String outputPath = "0efaa94a-b3f6-468b-8415-0ffc21904a49/" + System.currentTimeMillis();
            String segmentNaming = "seg_%number%.m4s";
            String initSegmentName = "init.mp4";

            FMP4Muxing videoMuxing = new FMP4Muxing();

            MuxingStream muxingStream = new MuxingStream();
            muxingStream.setStreamId(streamVideo.getId());

            EncodingOutput videoMuxingOutput = new EncodingOutput();
            videoMuxingOutput.setOutputId(outputId);
            videoMuxingOutput.setOutputPath(String.format("%s%s", outputPath, "/video/1024_1500000/fmp4/"));
            videoMuxingOutput.setAcl(aclEntries);

            videoMuxing.setSegmentLength(segmentLength);
            videoMuxing.setSegmentNaming(segmentNaming);
            videoMuxing.setInitSegmentName(initSegmentName);
            videoMuxing.addStream(muxingStream);
            videoMuxing.addOutput(videoMuxingOutput);

            videoMuxing = bitmovin.encoding.muxing.addFmp4MuxingToEncoding(encoding, videoMuxing);

            // Starts encoding
            bitmovin.encoding.start(encoding);
        } catch (IOException | UnirestException | BitmovinApiException | URISyntaxException | RestException e) {
            e.printStackTrace();
        }
    }

}
