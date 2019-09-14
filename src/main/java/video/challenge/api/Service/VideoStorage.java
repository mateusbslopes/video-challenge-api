package video.challenge.api.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoStorage {

    protected static String path = "/home/mateus/workfolder/storage";

    public static void save(MultipartFile video){
        Regions clientRegion = Regions.SA_EAST_1;
        String bucketName = "video-challenge-api";

        String stringObjKeyName = "test1";
        String fileObjKeyName = "test1";
        String fileName = "test1";

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();

            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

}
