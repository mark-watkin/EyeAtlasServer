package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag find(String name);

    void delete(Tag tag);

    String upload(Tag tagDto);
}
