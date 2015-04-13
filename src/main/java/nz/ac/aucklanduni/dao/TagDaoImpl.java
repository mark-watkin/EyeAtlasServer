package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Tag;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

@Repository("TagDao")
public class TagDaoImpl implements TagDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    public List<Tag> findAll( ) {
        return sessionFactory.getCurrentSession().createQuery("from Tag t").list();
    }

    @Override
    public void save(Tag tagDto) {
        sessionFactory.getCurrentSession().save(tagDto);
    }
}
