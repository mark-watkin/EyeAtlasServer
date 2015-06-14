package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.dao.CategoryDao;
import nz.ac.aucklanduni.model.Category;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryCreateService categoryCreateService;

    /**
     * If this method is called with null or an empty as the parent parameter,
     * category is added as a child of the root.
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String createCategory(Category category, String parent) {
        //TODO - should not be able to add category to parent referenced by condition
        return categoryCreateService.createCategory(category, parent);
    }

    /**
     * Creates category as child of root.
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String createCategory(Category category) {
        return createCategory(category, null);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public List<Category> findRoots() {
        return categoryDao.findRoots();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public Category find(String name) {
        return categoryDao.find(name);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String delete(String id) {
        try {
            Category category = this.find(id);
            categoryDao.delete(category);
        } catch (IllegalArgumentException e) {
            return "A category with name " + id + " has not been defined!";
        }
        return "The category was successfully deleted!";
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public List<Category> getCategories(String parent) {
        return categoryDao.getCategories(parent);
    }
}
