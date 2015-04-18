package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Category;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("CategoryDao")
public class CategoryDaoImpl implements CategoryDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Category find(String name) {
        return (Category) sessionFactory.getCurrentSession().createCriteria(Category.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public void delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
    }

    @Override
    public void save(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public List findRoots() {
        return sessionFactory.getCurrentSession().createQuery("from Category where parent = null").list();
    }
}
