package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Category;

import java.util.List;

public interface CategoryDao {

    public Category find(String name);

    public void delete(Category category);

    public void save(Category categoryDto);

    List findRoots();
}
