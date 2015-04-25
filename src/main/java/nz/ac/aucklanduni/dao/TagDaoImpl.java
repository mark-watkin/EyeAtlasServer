package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Tag;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Repository("TagDao")
@Transactional
public class TagDaoImpl implements TagDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Tag> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Tag t").list();
    }

    @Override
    public void save(Tag tagDto) {
        sessionFactory.getCurrentSession().save(tagDto);
    }

    @Override
    public Tag find(String name) {
        return (Tag) sessionFactory.getCurrentSession().createCriteria(Tag.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public void delete(Tag tagDto) {
        sessionFactory.getCurrentSession().delete(tagDto);
    }
}