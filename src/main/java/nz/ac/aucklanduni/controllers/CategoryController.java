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
        if (categoryUpload.getCategory().getName() == null || categoryUpload.getCategory().getName().equals("")) {
            return "The category name must not be empty!";
        }
        try {
            return categoryService.createCategory(categoryUpload.getCategory(), categoryUpload.getParentName());
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return "A category with name " + categoryUpload.getCategory().getName() + " has already been defined!";
        }
    }

    @RequestMapping(value = {"/rest/category/"}, method = RequestMethod.DELETE)
    public String categoryDelete() {
        return "The category name must not be empty!";
    }

    @RequestMapping(value = {"/rest/category/{name}"}, method = RequestMethod.DELETE)
    public String categoryDelete(@PathVariable("name") String name) {
        try {
            Category category = categoryService.find(name);
            categoryService.delete(category);
            return "The tag was successfully deleted!";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "A category with name " + name + " has not been defined!";
        }
    }
}
