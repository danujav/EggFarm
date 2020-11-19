package lk.rangafarm.pos.dao;

import lk.rangafarm.pos.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T extends SuperEntity, ID extends Serializable> extends SuperDao{
    public boolean save(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(ID id) throws Exception;

    public T search(ID id) throws Exception;

    public List<T> getAll() throws Exception;
}
