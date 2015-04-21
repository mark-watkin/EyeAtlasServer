package nz.ac.aucklanduni.dao;

import nz.ac.aucklanduni.model.InfoEntity;

import java.util.List;

public interface InfoEntityDao {

    public List<InfoEntity> findAll();

    public InfoEntity find(String title);
    public InfoEntity find(Integer id);

    public void save(InfoEntity infoEntityDto);

    public void delete(Integer id);
    public void delete(String title);
    public void delete(InfoEntity infoEntityDto);

}
