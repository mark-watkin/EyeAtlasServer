package nz.ac.aucklanduni.controllers;

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
//    public void postImage(@PathVariable("id") int id) {
//
//    }
    public @ResponseBody String postImage(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                return "You successfully uploaded !";
            } catch (Exception e) {
                return "You failed to upload  => " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty.";
        }
    }


    @RequestMapping(value = { "/rest/image/{id}" }, method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable("id") int id) {

    }

}
