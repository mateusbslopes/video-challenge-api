package video.challenge.api.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;
import video.challenge.api.exception.VideoChallengeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class to sent the videos to be saved on Amazon.
 */
public class VideoStorage {

    protected static String path = "/home/mateus/workfolder/storage";

    /**
     * Convert the video from Spring MultipartFile to Native java format.
     * @param fileToConvert File to be converted.
     * @return The converted file.
     */
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

    /**
     * Saves the file on amazon.
     * @param video Video to be saved.
     */
    public static void save(MultipartFile video) throws VideoChallengeException {
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
            throw new VideoChallengeException("Could not contact amazon server.");
        }
    }

}
