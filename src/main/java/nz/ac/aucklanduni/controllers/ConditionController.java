package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.Condition;
import nz.ac.aucklanduni.model.ConditionUpload;
import nz.ac.aucklanduni.model.Tag;
import nz.ac.aucklanduni.service.CategoryService;
import nz.ac.aucklanduni.service.ConditionService;
import nz.ac.aucklanduni.util.ImageProcessor;
import nz.ac.aucklanduni.util.S3ImageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ConditionController {

    @Autowired
    private ConditionService conditionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagDao tagDao;

    // Get Request for ALL
    @RequestMapping(value = "/rest/condition/all/{startIndex}/{endIndex}", method = RequestMethod.GET)
    public @ResponseBody
    List<Condition> getAllConditions(@PathVariable("startIndex") int startIndex,@PathVariable("endIndex") int endIndex) {
        return conditionService.findAllConditions(startIndex, endIndex);
    }

    @RequestMapping(value = "/rest/condition/all/count", method = RequestMethod.GET)
    public @ResponseBody
    Long getAllConditionCount() {
        return conditionService.getAllConditionsCount();
    }

    // Get Request for Category
    @RequestMapping(value = "/rest/condition/category/{title}/{startIndex}/{endIndex}", method = RequestMethod.GET)
    public @ResponseBody
    List<Condition> getCategoryConditions(@PathVariable("title") String title, @PathVariable("startIndex") int startIndex,@PathVariable("endIndex") int endIndex) {
        return conditionService.findCategoryConditions(title, startIndex, endIndex);
    }

    @RequestMapping(value = "/rest/condition/category/{title}/count", method = RequestMethod.GET)
    public @ResponseBody
    Long getCategoryConditionCount(@PathVariable("title") String title) {
        return conditionService.getCategoryConditionsCount(title);
    }

    // Get Request for Search term
    @RequestMapping(value = "/rest/condition/search/{term}/{startIndex}/{endIndex}", method = RequestMethod.GET)
    public @ResponseBody
    List<Condition> getSearchConditions(@PathVariable("term") String term, @PathVariable("startIndex") int startIndex,@PathVariable("endIndex") int endIndex) {
        return conditionService.findSearchConditions(term, startIndex, endIndex);
    }

    @RequestMapping(value = "/rest/condition/search/{term}/count", method = RequestMethod.GET)
    public @ResponseBody
    Long getSearchConditionCount(@PathVariable("term") String term) {
        return conditionService.getSearchConditionsCount(term);
    }


    // Post Request for conditions
    @RequestMapping(value = "/rest/condition", method = RequestMethod.POST)
    public String conditionUpload(@RequestBody ConditionUpload conditionUpload) {
        if (conditionUpload.getCategory() == null || conditionUpload.getCategory().equals("")) {
            return "The Condition must have a category!";
        }

        Condition condition = conditionUpload.getCondition();
        condition.setCategory(categoryService.find(conditionUpload.getCategory()));

        Set<Tag> tagSet = new HashSet<Tag>();
        for(String name : conditionUpload.getTags()) {
            tagSet.add(tagDao.find(name));
        }

        condition.setTags(tagSet);

        ImageProcessor.uploadImage(condition.getTitle(), conditionUpload.getImage());
        return conditionService.createCondition(condition);
    }

    // Delete request for conditions
    @RequestMapping(value = "/rest/condition/{name}", method = RequestMethod.DELETE)
    public String conditionDelete(@PathVariable("name") String name) {

        String result =  this.conditionService.delete(name);
        try {
            ImageProcessor.deleteImage(name);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

}
