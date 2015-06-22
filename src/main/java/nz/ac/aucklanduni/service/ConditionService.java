package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.model.Condition;

import java.util.List;

public interface ConditionService {

    public Condition createCondition(Condition condition);

    public List<Condition> findAllConditions();

    public List<Condition> findAllConditions(int startIndex, int endIndex);

    public Long getAllConditionsCount();

    public List<Condition> findCategoryConditions(String categoryId);

    public List<Condition> findCategoryConditions(String categoryId, int startIndex, int endIndex);

    public Long getCategoryConditionsCount(String categoryId);

    public List<Condition> findSearchConditions(String term);

    public List<Condition> findSearchConditions(String term, int startIndex, int endIndex);

    public Long getSearchConditionsCount(String term);

    public Condition find(Integer id);

    public boolean delete(Integer id);

    public void delete(Condition condition);

}
