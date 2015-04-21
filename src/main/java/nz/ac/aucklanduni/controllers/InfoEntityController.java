package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.InfoEntityUpload;
import nz.ac.aucklanduni.model.InfoEntity;
import nz.ac.aucklanduni.model.Tag;
import nz.ac.aucklanduni.service.CategoryService;
import nz.ac.aucklanduni.service.InfoEntityService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class InfoEntityController {

    @Autowired
    private InfoEntityService infoEntityService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value = "/rest/infoentity", method = RequestMethod.GET)
    public @ResponseBody
    InfoEntity getInfoEntity(@PathVariable("id") int id) {
        //temporary testing
        InfoEntity infoEntityTemp = new InfoEntity();
        infoEntityTemp.setId(1);
        infoEntityTemp.setTitle("temp");
        infoEntityTemp.setDescription("temp desc");
        return infoEntityTemp;
    }

    @RequestMapping(value = "/rest/infoentity", method = RequestMethod.POST)
    public String infoEntityUpload(@RequestBody InfoEntityUpload infoEntityUpload) {

        if (infoEntityUpload.getCategory() == null) {
            return "The Entity name must have a category!";
        }
        try {

            InfoEntity infoEntity = infoEntityUpload.getInfoEntity();
            infoEntity.setCategory(categoryService.find(infoEntityUpload.getCategory()));

            Set<Tag> tagSet = new HashSet<Tag>();
            for(Integer i : infoEntityUpload.getTags()) {
                tagSet.add(tagDao.find(i));
            }

            infoEntity.setTags(tagSet);

            return infoEntityService.createInfoEntity(infoEntity);

        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return "An entity with name " + infoEntityUpload.getInfoEntity().getTitle() + " has already been defined!";
        }
    }

    @RequestMapping(value = "/rest/infoentity/{name}", method = RequestMethod.DELETE)
    public void infoEntityDelete(@PathVariable("name") String name) {

    }

}
