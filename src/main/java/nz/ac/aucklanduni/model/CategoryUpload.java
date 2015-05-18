package nz.ac.aucklanduni.model;

public class CategoryUpload {
    private Category category;
    private String parentId;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "{ category: " + category.toString() + ", parent: " + parentId + " }";
    }
}
