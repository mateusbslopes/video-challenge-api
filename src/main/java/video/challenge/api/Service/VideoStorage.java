package video.challenge.api.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoStorage {

    protected static String path = "/home/mateus/workfolder/storage";

    public static void save(MultipartFile video){
        Path filePath = Paths.get(path, video.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(video.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
