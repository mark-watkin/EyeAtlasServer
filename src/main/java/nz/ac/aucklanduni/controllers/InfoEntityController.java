package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.model.InfoEntityUpload;
import nz.ac.aucklanduni.model.InfoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class InfoEntityController {

    @RequestMapping(value = "/rest/infoentity", method = RequestMethod.GET)
    public @ResponseBody
    InfoEntity getInfoEntity(@PathVariable("id") int id) {
        //temporary testing
        InfoEntity infoEntityTemp = new InfoEntity();
        infoEntityTemp.setId(1);
        infoEntityTemp.setTitle("temp");
        infoEntityTemp.setDescription("temp desc");
        return infoEntityTemp;
    }

    @RequestMapping(value = "/rest/infoentity", method = RequestMethod.POST)
    public void infoEntityUpload(@RequestBody InfoEntityUpload infoEntityUpload) {

    }

    @RequestMapping(value = "/rest/infoentity/{name}", method = RequestMethod.DELETE)
    public void infoEntityDelete(@PathVariable("name") String name) {

    }

}
