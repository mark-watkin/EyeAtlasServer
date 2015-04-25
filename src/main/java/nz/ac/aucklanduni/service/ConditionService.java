package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.model.Condition;

import java.util.List;

public interface ConditionService {

    public String createCondition(Condition condition);

    public List<Condition> findAll();

    public Condition find(String title);

    public void save(Condition conditionDto);

    public void delete(String title);
    public void delete(Condition conditionDto);

}
