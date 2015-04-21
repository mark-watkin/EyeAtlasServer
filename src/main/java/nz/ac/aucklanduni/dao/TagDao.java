package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Tag;

import java.util.List;

public interface TagDao {
    public List<Tag> findAll();

    public void save(Tag tagDto);

    public Tag find(String name);

    public Tag find(Integer id);

    public void delete(Tag tagDto);
}