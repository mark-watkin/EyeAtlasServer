package nz.ac.aucklanduni.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "entity")
public class InfoEntity {

    private long id;
    private String title;
    private String description;
    private Category category;
    private Set<Tag> tags = new HashSet<Tag>();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    public Category getCategory() { return category; }

    public void setCategory(Category category) {
        this.category = category;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "entity_tag",
            joinColumns = @JoinColumn(name = "E_id", nullable = false, updatable = false) ,
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
}
