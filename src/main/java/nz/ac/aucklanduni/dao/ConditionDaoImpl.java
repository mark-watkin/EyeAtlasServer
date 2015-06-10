package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Condition;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("ConditionDao")
public class ConditionDaoImpl implements ConditionDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Condition> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Condition i").list();
    }

    @Override
    public Condition find(String title) {
        return (Condition) sessionFactory.
                getCurrentSession().
                createCriteria(Condition.class).
                add(Restrictions.eq("title", title)).
                uniqueResult();
    }

    @Override
    public Long getConditionCount() {
        return (Long)sessionFactory.
                getCurrentSession().
                createQuery("select count(i) from Condition i").
                uniqueResult();
    }

    @Override
    public void save(Condition conditionDto) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(conditionDto);
    }

    @Override
    public void delete(Condition conditionDto) {
        this.sessionFactory.getCurrentSession().delete(conditionDto);
    }
}
