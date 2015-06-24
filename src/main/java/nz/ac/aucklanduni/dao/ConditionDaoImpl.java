package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Condition;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.exception.EmptyQueryException;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("ConditionDao")
public class ConditionDaoImpl implements ConditionDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void buildSearchIndex() {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        try {
            fullTextSession.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        // create native Lucene query unsing the query DSL
        // alternatively you can write the Lucene query using the Lucene query parser
        // or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity( Condition.class ).get();

        try {
            org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("title", "description", "category.name", "tags.name")
                .matching(term)
                .createQuery();

            // wrap Lucene query in a org.hibernate.Query
            org.hibernate.Query hibQuery =
                    fullTextSession.createFullTextQuery(query, Condition.class);

            // execute search
            List<Condition> result = hibQuery.list();

            return result;

        } catch (EmptyQueryException e) {
            return new ArrayList<Condition>();
        }
    }

    @Override
    public List<Condition> findSearchConditions(String term, int startIndex, int endIndex) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        QueryBuilder qb = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity( Condition.class ).get();
        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("title", "description", "category.name", "tags.name")
                .matching(term)
                .createQuery();

        // wrap Lucene query in a org.hibernate.Query
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Condition.class);

        // Limit dataset range
        hibQuery.setFirstResult(startIndex);
        hibQuery.setMaxResults(endIndex - startIndex);

        // execute search
        List<Condition> result = hibQuery.list();

        return result;
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
