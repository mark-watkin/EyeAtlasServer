package nz.ac.aucklanduni.model;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

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