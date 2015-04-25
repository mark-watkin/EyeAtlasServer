package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.Tag;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value = {"/rest/tag"}, method = RequestMethod.GET)
    public List<Tag> tagResolver() {
        return tagDao.findAll();
    }

    @RequestMapping(value = {"/rest/tag"}, method = RequestMethod.POST, headers = {"content-type=application/json"})
    public String tagUpload(@RequestBody Tag tagDto) {
        if (tagDto.getName() == null || tagDto.getName().equals("")) {
            return "The tag name must not be empty!";
        }
        try {
            tagDao.save(tagDto);
            return "The tag was successfully added!";
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return "A tag with name " + tagDto.getName() + " has already been defined!";
        }
    }

    @RequestMapping(value = {"/rest/tag/"}, method = RequestMethod.DELETE)
    public String tagDelete() {
        return "The tag name must not be empty!";
    }

    @RequestMapping(value = {"/rest/tag/{name}"}, method = RequestMethod.DELETE)
    public String tagDelete(@PathVariable("name") String name) {
        try {
            Tag tag = tagDao.find(name);
            tagDao.delete(tag);
            return "The tag was successfully deleted!";
        } catch (IllegalArgumentException e) {
            return "A tag with name " + name + " has not been defined!";
        }
    }
}