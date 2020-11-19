package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.Cage;

import java.util.ArrayList;

public interface CageDao extends CrudDao<Cage, String> {
    public int getHensCount() throws Exception;

    public ArrayList<String> getCageNames() throws Exception;

    public String getCageId() throws Exception;
}
