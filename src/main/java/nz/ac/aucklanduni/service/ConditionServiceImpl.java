package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.dao.ConditionDao;
import nz.ac.aucklanduni.model.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("ConditionService")
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ConditionDao conditionDao;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String createCondition(Condition condition) {
        if (this.conditionDao.find(condition.getTitle()) != null) {
            return "Failed to create condition, it already exists!";
        }
        conditionDao.save(condition);
        return "Condition was successfully created!";

    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public List<Condition> findAll() {
        return this.conditionDao.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public Condition find(String title) {
        return this.conditionDao.find(title);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void save(Condition ConditionDto) {
        this.conditionDao.save(ConditionDto);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(String title) {
        this.conditionDao.delete(title);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Condition conditionDto) {
        this.createCondition(conditionDto);
    }
}
