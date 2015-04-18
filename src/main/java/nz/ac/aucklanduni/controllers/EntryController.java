package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.model.EntityUpload;
import nz.ac.aucklanduni.model.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {

    @RequestMapping(value = "/rest/entry", method = RequestMethod.GET)
    public @ResponseBody Entry getEntry(@PathVariable("id") int id) {
        //temporary testing
        Entry entryTemp = new Entry();
        entryTemp.setId(id);
        entryTemp.setName("temp");
        return entryTemp;
    }

    @RequestMapping(value = "/rest/entry", method = RequestMethod.POST)
    public void entryUpload(@RequestBody EntityUpload entityUpload) {

    }

    @RequestMapping(value = "/rest/entry/{name}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable("name") String name) {

    }

}
