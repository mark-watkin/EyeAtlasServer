package nz.ac.aucklanduni.model;

import java.util.ArrayList;
import java.util.List;

public class InfoEntityUpload {

    private InfoEntity infoEntity;

    private Integer category;
    private List<Integer> tags = new ArrayList<Integer>();
    private String image;


    public InfoEntity getInfoEntity() { return infoEntity; }

    public void setInfoEntity(InfoEntity infoEntity) { this.infoEntity = infoEntity; }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<Integer> getTags() { return tags; }

    public void setTags(List<Integer> tags) {
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
        return "{ infoEntity: " + infoEntity.toString() + ", category: " + category + ", tags: " + tags + " }";
    }

}
