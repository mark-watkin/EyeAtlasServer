package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.model.Tag;
import nz.ac.aucklanduni.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = {"/rest/tag"}, method = RequestMethod.GET)
    public List<Tag> tagResolver() {
        return tagService.findAll();
    }

    @RequestMapping(value = {"/rest/tag"}, method = RequestMethod.POST, headers = {"content-type=application/json"})
    public String tagUpload(@RequestBody Tag tagDto) {
        return tagService.upload(tagDto);
    }

    @RequestMapping(value = {"/rest/tag/"}, method = RequestMethod.DELETE)
    public String tagDelete() {
        return "The tag name must not be empty!";
    }

    @RequestMapping(value = {"/rest/tag/{name}"}, method = RequestMethod.DELETE)
    public String tagDelete(@PathVariable("name") String name) {
        try {
            Tag tag = tagService.find(name);
            tagService.delete(tag);
            return "The tag was successfully deleted!";
        } catch (IllegalArgumentException e) {
            return "A tag with name " + name + " has not been defined!";
        }
    }
}