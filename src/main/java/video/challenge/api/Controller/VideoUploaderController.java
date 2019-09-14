package video.challenge.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import video.challenge.api.Service.VideoStorage;

@Controller
public class VideoUploaderController {

    @PostMapping
    public void uploadVideo(@RequestParam("file")MultipartFile video){
        VideoStorage.save(video);
    }


}
