package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.Condition;
import nz.ac.aucklanduni.model.ConditionUpload;
import nz.ac.aucklanduni.model.Tag;
import nz.ac.aucklanduni.service.CategoryService;
import nz.ac.aucklanduni.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class ConditionController {

    @Autowired
    private ConditionService conditionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value = "/rest/condition/{title}", method = RequestMethod.GET)
    public @ResponseBody
    Condition getCondition(@PathVariable("title") String title) {
        return conditionService.find(title);
    }

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

        return conditionService.createCondition(condition);
    }

    @RequestMapping(value = "/rest/condition/{name}", method = RequestMethod.DELETE)
    public String conditionDelete(@PathVariable("name") String name) {
        return this.conditionService.delete(name);
    }

}
