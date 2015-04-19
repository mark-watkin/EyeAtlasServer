package nz.ac.aucklanduni.model;

import java.util.ArrayList;
import java.util.List;

public class InfoEntityUpload {
    private String name;
    private String description;
    private String category;
    private List<String> tags = new ArrayList<String>();
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "{ name: " + name + ", category: " + category + ", tags: " + tags + " }";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
