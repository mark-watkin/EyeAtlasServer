package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.InfoEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("InfoEntityDao")
public class InfoEntityDaoImpl implements InfoEntityDao {

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<InfoEntity> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from InfoEntity i").list();
    }

    @Override
    public InfoEntity find(String title) {
        return (InfoEntity) sessionFactory.
                getCurrentSession().
                createCriteria(InfoEntity.class).
                add(Restrictions.eq("title", title)).
                uniqueResult();
    }

    @Override
    public InfoEntity find(Integer id) {
        return (InfoEntity) sessionFactory.
                getCurrentSession().
                createCriteria(InfoEntity.class).
                add(Restrictions.eq("id", id)).
                uniqueResult();
    }

    @Override
    public void save(InfoEntity infoEntityDto) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(infoEntityDto);
    }

    @Override
    public void delete(Integer id) {
        this.sessionFactory.getCurrentSession().delete(find(id));
    }

    @Override
    public void delete(String title) {
        this.sessionFactory.getCurrentSession().delete(find(title));
    }

    @Override
    public void delete(InfoEntity infoEntityDto) {
        this.sessionFactory.getCurrentSession().delete(infoEntityDto);
    }
}
