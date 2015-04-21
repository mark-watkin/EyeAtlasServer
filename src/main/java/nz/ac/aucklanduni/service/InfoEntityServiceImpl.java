package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.dao.InfoEntityDao;
import nz.ac.aucklanduni.model.Category;
import nz.ac.aucklanduni.model.InfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("InfoEntityService")
public class InfoEntityServiceImpl implements InfoEntityService {

    @Autowired
    private InfoEntityDao infoEntityDao;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String createInfoEntity(InfoEntity infoEntity) {
        infoEntityDao.save(infoEntity);
        return "InfoEntity was successfully created!";

    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public List<InfoEntity> findAll() {
        return this.infoEntityDao.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public InfoEntity find(String title) {
        return this.infoEntityDao.find(title);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public InfoEntity find(Integer id) {
        return this.infoEntityDao.find(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void save(InfoEntity infoEntityDto) {
        this.infoEntityDao.save(infoEntityDto);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Integer id) {
        this.infoEntityDao.delete(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(String title) {
        this.infoEntityDao.delete(title);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(InfoEntity infoEntityDto) {
        this.createInfoEntity(infoEntityDto);
    }
}
