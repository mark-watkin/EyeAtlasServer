package nz.ac.aucklanduni.model;

public class CategoryUpload {
    private Category category;
    private String parentName;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "{ category: " + category.toString() + ", parent: " + parentName + " }";
    }
}
