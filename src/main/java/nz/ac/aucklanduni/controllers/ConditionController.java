package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.Condition;
import nz.ac.aucklanduni.model.ConditionUpload;
import nz.ac.aucklanduni.model.Tag;
import nz.ac.aucklanduni.service.CategoryService;
import nz.ac.aucklanduni.service.ConditionService;
import org.hibernate.exception.ConstraintViolationException;
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
        //temporary testing
        Condition conditionTemp = new Condition();
        conditionTemp.setTitle("temp");
        conditionTemp.setDescription("temp desc");
        return conditionTemp;
    }

    @RequestMapping(value = "/rest/condition", method = RequestMethod.POST)
    public String conditionUpload(@RequestBody ConditionUpload conditionUpload) {

        if (conditionUpload.getCategory() == null) {
            return "The Entity name must have a category!";
        }
        try {

            Condition condition = conditionUpload.getCondition();
            condition.setCategory(categoryService.find(conditionUpload.getCategory()));

            Set<Tag> tagSet = new HashSet<Tag>();
            for(String name : conditionUpload.getTags()) {
                tagSet.add(tagDao.find(name));
            }

            condition.setTags(tagSet);

            return conditionService.createCondition(condition);

        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return "An entity with name " + conditionUpload.getCondition().getTitle() + " has already been defined!";
        }
    }

    @RequestMapping(value = "/rest/condition/{name}", method = RequestMethod.DELETE)
    public void conditionDelete(@PathVariable("name") String name) {

    }

}
