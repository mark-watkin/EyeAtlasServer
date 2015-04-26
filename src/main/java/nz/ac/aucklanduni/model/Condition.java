package nz.ac.aucklanduni.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "condition")
public class Condition {

    private String title;
    private String description;
    private Category category;
    private Set<Tag> tags = new HashSet<Tag>();

    @Id
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


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    public Category getCategory() { return category; }

    public void setCategory(Category category) {
        this.category = category;
    }


    @ManyToMany(fetch = FetchType.LAZY)
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
        return "{ title: " + title + ", desc :" + description + ", category :" + category
                + ", tags :" + tags + " }";
    }
}
