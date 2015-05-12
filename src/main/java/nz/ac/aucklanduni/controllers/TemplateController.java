package nz.ac.aucklanduni.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TemplateController {

    @RequestMapping(value = {"/template/tag_select"}, method = RequestMethod.GET)
    public ModelAndView getTagSelect() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/tag_select");
        return model;
    }

    @RequestMapping(value = {"/template/tag_display"}, method = RequestMethod.GET)
    public ModelAndView getTagDisplay() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/tag_display");
        return model;
    }

    @RequestMapping(value = {"/template/category_select"}, method = RequestMethod.GET)
    public ModelAndView getCategorySelect() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/category_select");
        return model;
    }

    @RequestMapping(value = {"/template/category_display"}, method = RequestMethod.GET)
    public ModelAndView getCategoryDisplay() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/category_display");
        return model;
    }
}
