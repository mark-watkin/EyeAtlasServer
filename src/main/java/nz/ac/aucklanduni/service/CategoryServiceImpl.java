package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.dao.CategoryDao;
import nz.ac.aucklanduni.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * If this method is called with null or an empty as the parent parameter,
     * category is added as a child of the root.
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String createCategory(Category category, String parent) {
        if(parent == null || parent.equals("")) {
            return createCategory(category);
        }

        Category categoryParent = categoryDao.find(parent);
        if (categoryParent == null) {
            return "No category parent defined with name " + parent;
        }

        category.setParent(categoryParent);
        categoryDao.save(category);
        return "Category was successfully created!";
    }


    /**
     * Creates category as child of root.
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String createCategory(Category category) {
        categoryDao.save(category);
        return "Category was successfully created!";
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
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public Category find(Integer id) { return categoryDao.find(id); }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Category category) {
        categoryDao.delete(category);
    }
}
