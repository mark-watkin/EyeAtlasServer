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
    public @ResponseBody String postImage(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                return "redirect:success";
            } catch (Exception e) {
                return "redirect:failed";
            }
        } else {
            return "redirect:failed";
        }
    }


    @RequestMapping(value = { "/rest/image/{id}" }, method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable("id") int id) {

    }

}
