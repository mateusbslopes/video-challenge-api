package video.challenge.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import video.challenge.api.exception.VideoChallengeException;
import video.challenge.api.service.Encoding;
import video.challenge.api.service.VideoStorage;

/**
 * Controller to handle the uploadVideo request.
 */
@Controller
public class VideoUploaderController {

    /**
     * Saves a file on amazon cloud and sent it to encode.
     * @param video Video to be sent and encoded.
     */
    @PostMapping
    public String uploadVideo(@RequestParam("file")MultipartFile video){
        try{
            VideoStorage.save(video);
            Encoding.encode();
        } catch (VideoChallengeException e){
            return "An error occur: " + e.getMessage();
        }
        return "Video successfully encoded!";
    }
}
