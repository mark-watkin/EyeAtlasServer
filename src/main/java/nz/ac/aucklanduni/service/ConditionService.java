package nz.ac.aucklanduni.service;

import nz.ac.aucklanduni.model.Condition;

import java.util.List;

public interface ConditionService {

    public String createCondition(Condition condition);

    public List<Condition> findAllConditions();

    public List<Condition> findAllConditions(int startIndex, int endIndex);

    public Long getAllConditionsCount();

    public List<Condition> findCategoryConditions(String title, int startIndex, int endIndex);

    public Long getCategoryConditionsCount(String title);

    public List<Condition> findSearchConditions(String term, int startIndex, int endIndex);

    public Long getSearchConditionsCount(String term);

    public Condition find(String title);



    public String delete(String title);

    public void delete(Condition conditionDto);

}
