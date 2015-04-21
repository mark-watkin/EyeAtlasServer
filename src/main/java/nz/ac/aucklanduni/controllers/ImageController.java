package nz.ac.aucklanduni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
public class ImageController {

    @RequestMapping(value = { "/rest/image/{id}/{resolution}/{col}/{row}" }, method = RequestMethod.GET)
    public @ResponseBody Image getImage(@PathVariable("id") int id, @PathVariable("resolution") int resolution, @PathVariable("col") int col, @PathVariable("row") int row) {
        return null;
    }
}
