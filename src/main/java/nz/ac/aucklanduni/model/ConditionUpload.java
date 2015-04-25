package nz.ac.aucklanduni.model;

import java.util.ArrayList;
import java.util.List;

public class ConditionUpload {

    private Condition condition;
    private String category;
    private List<String> tags = new ArrayList<String>();
    private String image;


    public Condition getCondition() { return condition; }

    public void setCondition(Condition condition) { this.condition = condition; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() { return tags; }

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
        return "{ condition: " + condition.toString() + ", category: " + category + ", tags: " + tags + " }";
    }

}
