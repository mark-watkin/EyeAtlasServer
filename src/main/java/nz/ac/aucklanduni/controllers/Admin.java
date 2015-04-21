package nz.ac.aucklanduni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Admin {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView landingPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("entity");
        return model;
    }

    @RequestMapping(value = { "/infoentity" }, method = RequestMethod.GET)
    public ModelAndView entity() {
        ModelAndView model = new ModelAndView();
        model.setViewName("infoentity");
        return model;
    }

    @RequestMapping(value = { "/tag" }, method = RequestMethod.GET)
    public ModelAndView tag() {
        ModelAndView model = new ModelAndView();
        model.setViewName("tag");
        return model;
    }

    @RequestMapping(value = { "/category" }, method = RequestMethod.GET)
    public ModelAndView category() {
        ModelAndView model = new ModelAndView();
        model.setViewName("category");
        return model;
    }
}
