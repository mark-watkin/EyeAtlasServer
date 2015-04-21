package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Category;

import java.util.List;

public interface CategoryDao {

    public Category find(String name);
    public Category find(Integer id);

    public void delete(Category category);

    public void save(Category categoryDto);

    public List findRoots();
}
