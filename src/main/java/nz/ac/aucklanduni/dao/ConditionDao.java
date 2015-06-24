package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.Condition;

import java.util.List;

public interface ConditionDao {

    public void buildSearchIndex();

    public List<Condition> findAllConditions();

    public List<Condition> findAllConditions(int startIndex, int endIndex);

    public Long getAllConditionsCount();

    public List<Condition> findCategoryConditions(String categoryId);

    public List<Condition> findCategoryConditions(String categoryId, int startIndex, int endIndex);

    public Long getCategoryConditionsCount(String categoryId);

    public List<Condition> findSearchConditions(String term);

    public List<Condition> findSearchConditions(String term, int startIndex, int endIndex);

    public Condition find(Integer id);

    public void save(Condition condition);

    public void delete(Condition condition);

}
