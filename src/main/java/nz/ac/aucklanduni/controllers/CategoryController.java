package nz.ac.aucklanduni.controllers;

import nz.ac.aucklanduni.model.Category;
import nz.ac.aucklanduni.model.CategoryUpload;
import nz.ac.aucklanduni.service.CategoryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/rest/category"}, method = RequestMethod.GET)
    public List<Category> categoryResolver() {
        return categoryService.findRoots();
    }

    @RequestMapping(value = {"/rest/category/{parent}"}, method = RequestMethod.GET)
    public List<Category> getCategories(@PathVariable("parent") String parent) {
        return categoryService.getCategories(parent);
    }

    @RequestMapping(value = {"/rest/category"}, method = RequestMethod.POST, headers = {"content-type=application/json"})
    public String categoryUpload(@RequestBody CategoryUpload categoryUpload) {
        return categoryService.createCategory(categoryUpload.getCategory(), categoryUpload.getParentId());
    }

    @RequestMapping(value = {"/rest/category/"}, method = RequestMethod.DELETE)
    public String categoryDelete() {
        return "The category name must not be empty!";
    }

    @RequestMapping(value = {"/rest/category/{id}"}, method = RequestMethod.DELETE)
    public String categoryDelete(@PathVariable("id") String id) {
        try {
            return categoryService.delete(id);
        } catch (Exception e ) {
            return e.getMessage();
        }
    }
}
