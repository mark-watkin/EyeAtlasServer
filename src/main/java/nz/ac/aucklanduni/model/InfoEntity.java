package nz.ac.aucklanduni.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "entity")
public class InfoEntity {

    private long id;
    private String title;
    private String description;
    private Category category;
    private Set<Tag> tags;

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
    @JoinTable(
        name = "entity_category",
        joinColumns = @JoinColumn(name = "E_id"),
        inverseJoinColumns = @JoinColumn(name = "C_id"))
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "entity_tags",
            joinColumns = @JoinColumn(name = "E_id", nullable = false, updatable = false) ,
            inverseJoinColumns =  @JoinColumn(name = "T_id", nullable = false, updatable = false) )
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
