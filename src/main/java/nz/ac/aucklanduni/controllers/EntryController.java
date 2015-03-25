package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.model.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/entry")
public class EntryController {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody Entry getEntry(@PathVariable("id") int id) {
        //temporary testing
        Entry entryTemp = new Entry();
        entryTemp.setId(id);
        entryTemp.setName("temp");
        return entryTemp;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void putEntry(@PathVariable("id") int id) {

    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable("id") int id) {

    }

}
