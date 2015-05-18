package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.dao.TagDao;
import nz.ac.aucklanduni.model.Tag;
import nz.ac.aucklanduni.util.Conditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TagService")
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public Tag find(String name) {
        return tagDao.find(name);
    }

    @Override
    public void delete(Tag tag) {
        tagDao.delete(tag);
    }

    @Override
    public String upload(Tag tagDto) {
        if (!Conditional.isStringSet(tagDto.getName())) {
            return "The tag name must not be empty!";
        } else if (Conditional.isSet(tagDao.find(tagDto.getName()))) {
            return "A tag with name " + tagDto.getName() + " has already been defined!";
        } else {
            tagDao.save(tagDto);
            return "The tag was successfully added!";
        }
    }
}
