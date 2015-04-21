package nz.ac.aucklanduni.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TemplateController {

    @RequestMapping(value = {"/template/tag"}, method = RequestMethod.GET)
    public ModelAndView getTag() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/tag");
        return model;
    }

    @RequestMapping(value = {"/template/category"}, method = RequestMethod.GET)
    public ModelAndView getCategory() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/category");
        return model;
    }
}
