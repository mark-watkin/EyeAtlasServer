package nz.ac.aucklanduni.model;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

    private String name;
    private String description;
    private Category parent;
    private List<Category> children = new ArrayList<Category>();

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{  name: " + name + ", children :" + children.toString() + " }";
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy="parent", fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    @OrderBy("name")
    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void addChild(Category category) {
        children.add(category);
    }
}
