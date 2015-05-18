package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.model.Category;

public interface CategoryCreateService {
    public String createCategory(Category category, String parent);
}
