package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.manager.UploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Controller
public class ImageController {

    @RequestMapping(value = { "/rest/image/{id}/{resolution}/{col}/{row}" }, method = RequestMethod.GET)
    public @ResponseBody Image getImage(@PathVariable("id") int id, @PathVariable("resolution") int resolution, @PathVariable("col") int col, @PathVariable("row") int row) {
        return null;
    }

    @RequestMapping(value = { "/rest/image" }, method = RequestMethod.POST)
    public @ResponseBody String postImage(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                new UploadManager().uplaod(file);
                return "Success.";
            } catch (Exception e) {
                return "Failed.";
            }
        } else {
            return "Failed: No file selected.";
        }
    }


    @RequestMapping(value = { "/rest/image/{id}" }, method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable("id") int id) {

    }

}
