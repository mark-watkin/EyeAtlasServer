package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Condition;

import java.util.List;

public interface ConditionDao {

    public List<Condition> findAll();

    public Condition find(String title);

    public void save(Condition conditionDao);

    public void delete(Condition conditionDto);

}
