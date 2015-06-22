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
        return sessionFactory.getCurrentSession().createQuery("from Condition c order by c.title").list();
    }

    @Override
    public List<Condition> findAllConditions(int startIndex, int endIndex) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Condition c");
        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex - startIndex);
        return query.list();
    }

    @Override
    public Long getAllConditionsCount() {
        return (Long)sessionFactory.getCurrentSession().createQuery("select count(c) from Condition c").uniqueResult();
    }

    @Override
    public List<Condition> findCategoryConditions(String categoryId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Condition c where category = :categoryId")
                .setString("categoryId", categoryId);
        return query.list();
    }

    @Override
    public List<Condition> findCategoryConditions(String categoryId, int startIndex, int endIndex) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Condition c where category = :categoryId")
                .setString("categoryId", categoryId);
        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex - startIndex);
        return query.list();
    }

    @Override
    public Long getCategoryConditionsCount(String categoryId) {
        return (Long)sessionFactory.getCurrentSession()
                .createQuery("select count(c) from Condition c where category = :categoryId")
                .setString("categoryId", categoryId)
                .uniqueResult();
    }

    @Override
    public List<Condition> findSearchConditions(String term) {
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
    public Condition find(Integer id) {
        return (Condition) sessionFactory.
                getCurrentSession().
                createCriteria(Condition.class).
                add(Restrictions.eq("id", id)).
                uniqueResult();
    }

    @Override
    public void save(Condition condition) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(condition);
    }

    @Override
    public void delete(Condition condition) {
        this.sessionFactory.getCurrentSession().delete(condition);
    }
}
