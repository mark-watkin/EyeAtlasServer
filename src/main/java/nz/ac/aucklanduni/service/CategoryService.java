package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.model.Category;

import java.util.List;

public interface CategoryService {
    /**
     * If this method is called with null or an empty as the parent parameter,
     * category is added as a child of the root.
     */
    public String createCategory(Category category, String parent);

    /**
     * Creates category as child of root.
     */
    String createCategory(Category category);

    public List<Category> findRoots();

    public Category find(String name);

    public Category find(Integer id);

    public void delete(Category category);
}
