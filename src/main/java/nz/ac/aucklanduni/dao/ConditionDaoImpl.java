package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Condition;
import org.hibernate.Query;
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
    public List<Condition> findAllConditions() {
        return sessionFactory.getCurrentSession().createQuery("from Condition i").list();
    }

    @Override
    public List<Condition> findAllConditions(int startIndex, int endIndex) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Condition i");
        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex - startIndex);
        return query.list();
    }

    @Override
    public Long getAllConditionsCount() {
        return (Long)sessionFactory.getCurrentSession().createQuery("select count(i) from Condition i").uniqueResult();
    }

    @Override
    public List<Condition> findCategoryConditions(String title, int startIndex, int endIndex) {
        return null;
    }

    @Override
    public Long getCategoryConditionsCount(String title) {
        return null;
    }

    @Override
    public List<Condition> findSearchConditions(String term, int startIndex, int endIndex) {
        return null;
    }

    @Override
    public Long getSearchConditionsCount(String term) {
        return null;
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
    public void save(Condition conditionDto) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(conditionDto);
    }

    @Override
    public void delete(Condition conditionDto) {
        this.sessionFactory.getCurrentSession().delete(conditionDto);
    }
}
