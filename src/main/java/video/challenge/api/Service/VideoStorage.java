package video.challenge.api.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VideoStorage {

    protected static String path = "/home/mateus/workfolder/storage";

    protected static File convertMultipartFileToFile(MultipartFile fileToConvert){
        File convertedFile = new File(fileToConvert.getOriginalFilename());
        try {
            convertedFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(fileToConvert.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertedFile;
    }

    public static void save(MultipartFile video){
        Regions clientRegion = Regions.SA_EAST_1;
        String bucketName = "video-challenge-api";

        String stringObjKeyName = "test1";
        File fileToUpload = convertMultipartFileToFile(video);

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();

            s3Client.putObject(bucketName, stringObjKeyName, fileToUpload);
            fileToUpload.delete();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

}
