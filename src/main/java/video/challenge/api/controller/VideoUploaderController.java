package video.challenge.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import video.challenge.api.service.Encoding;

@Controller
public class VideoUploaderController {

    @PostMapping
    public void uploadVideo(@RequestParam("file")MultipartFile video){
        Encoding.encode("a");
    }


}
