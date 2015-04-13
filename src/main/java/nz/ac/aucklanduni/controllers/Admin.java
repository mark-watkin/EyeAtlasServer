package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.dao.TagDaoImpl;
import nz.ac.aucklanduni.model.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Admin {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView entityAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }
}
