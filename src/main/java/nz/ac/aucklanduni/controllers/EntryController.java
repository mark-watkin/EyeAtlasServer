package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.model.EntityUpload;
import nz.ac.aucklanduni.model.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntryController {

    @RequestMapping(value = "/rest/entity", method = RequestMethod.GET)
    public @ResponseBody Entry getEntry(@PathVariable("id") int id) {
        //temporary testing
        Entry entryTemp = new Entry();
        entryTemp.setId(id);
        entryTemp.setName("temp");
        return entryTemp;
    }

    @RequestMapping(value = "/rest/entity", method = RequestMethod.POST)
    public String entryUpload(@RequestBody EntityUpload entityUpload) {
        return entityUpload.toString();
    }

    @RequestMapping(value = "/rest/entity/{name}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable("name") String name) {

    }

}
