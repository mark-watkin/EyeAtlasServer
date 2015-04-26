package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.Exceptions.ParentNotFoundException;
import nz.ac.aucklanduni.dao.CategoryDao;
import nz.ac.aucklanduni.model.Category;
import nz.ac.aucklanduni.util.Conditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CategoryCreateService")
public class CategoryCreateServiceImpl implements CategoryCreateService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public String createCategory(Category category, String parent) {
        if (!Conditional.isStringSet(category.getName())) {
            return "Category must have a name!";
        } else if (Conditional.isSet(categoryDao.find(category.getName()))) {
            return "Category " + category.getName() + " already exists!";
        }

        try {
            this.defineParent(category, parent);
        } catch (ParentNotFoundException e) {
            return parent + " is not a defined category!";
        }

        categoryDao.save(category);
        return "Category was successfully created!";
    }

    public void defineParent(Category category, String parent) throws ParentNotFoundException {
        if (Conditional.isStringSet(parent)) {
            Category categoryParent = categoryDao.find(parent);
            if (!Conditional.isSet(categoryParent)) {
                throw new ParentNotFoundException();
            }
            category.setParent(categoryParent);
        }
    }
}
