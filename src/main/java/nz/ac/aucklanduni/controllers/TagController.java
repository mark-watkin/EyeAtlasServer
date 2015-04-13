package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value = { "/rest/tags" }, method = RequestMethod.GET)
    public List<Tag> tagResolver() {
        return tagDao.findAll();
    }

    @RequestMapping(value = { "/rest/tags" }, method = RequestMethod.POST)
    public void tagUpload(@ModelAttribute("tag") Tag tagDto) {
        tagDao.save(tagDto);
    }
}
