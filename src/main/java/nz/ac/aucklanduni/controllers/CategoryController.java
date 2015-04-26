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

    @RequestMapping(value = {"/rest/category"}, method = RequestMethod.POST, headers = {"content-type=application/json"})
    public String categoryUpload(@RequestBody CategoryUpload categoryUpload) {
        return categoryService.createCategory(categoryUpload.getCategory(), categoryUpload.getParentName());
    }

    @RequestMapping(value = {"/rest/category/"}, method = RequestMethod.DELETE)
    public String categoryDelete() {
        return "The category name must not be empty!";
    }

    @RequestMapping(value = {"/rest/category/{name}"}, method = RequestMethod.DELETE)
    public String categoryDelete(@PathVariable("name") String name) {
        try {
            return categoryService.delete(name);
        } catch (Exception e ) {
            return "Cannot delete a category that is referenced by a condition";
        }
    }
}
