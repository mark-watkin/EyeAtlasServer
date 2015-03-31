package nz.ac.aucklanduni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Admin {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = { "/failed" }, method = RequestMethod.GET)
    public ModelAndView uplodFailed() {
        ModelAndView model = new ModelAndView();
        model.setViewName("failedUpload");
        return model;
    }

    @RequestMapping(value = { "/success" }, method = RequestMethod.GET)
    public ModelAndView uploadSuccess() {
        ModelAndView model = new ModelAndView();
        model.setViewName("successfulUpload");
        return model;
    }

}
