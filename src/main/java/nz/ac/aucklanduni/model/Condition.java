package nz.ac.aucklanduni.model;

import org.hibernate.annotations.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Indexed
@Table(name = "condition")
public class Condition {

    private Integer id;

    @Field
    private String title;

    @Field
    private String description;

    @IndexedEmbedded
    private Category category;

    @IndexedEmbedded
    private Set<Tag> tags = new HashSet<Tag>();

    private Integer imageWidth;

    private Integer imageHeight;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    public Category getCategory() { return category; }

    public void setCategory(Category category) {
        this.category = category;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "condition_tag",
            joinColumns = @JoinColumn(name = "C_id", nullable = false, updatable = false) ,
            inverseJoinColumns =  @JoinColumn(name = "T_id", nullable = false, updatable = false) )
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", title: " + title + ", desc :" + description + ", category :" + category
                + ", tags :" + tags + " }";
    }

    @Column(name = "img_res_width")
    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    @Column(name = "img_res_height")
    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }
}
