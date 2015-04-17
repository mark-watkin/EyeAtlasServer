package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Tag;

import java.util.List;

public interface TagDao {
    public List<Tag> findAll();

    public void save(Tag tagDto);

    Tag find(String name);

    void delete(Tag tagDto);
}
