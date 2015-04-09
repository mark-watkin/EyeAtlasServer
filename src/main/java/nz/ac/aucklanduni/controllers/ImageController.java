package nz.ac.aucklanduni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File("tmp");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename()); // TODO Replace original filename with a unique ID.
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();


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
