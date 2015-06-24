package nz.ac.aucklanduni.model;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

    @DocumentId
    private String name;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}