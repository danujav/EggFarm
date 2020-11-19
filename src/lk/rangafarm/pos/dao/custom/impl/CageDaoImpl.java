package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.CageDao;
import lk.rangafarm.pos.entity.Cage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CageDaoImpl implements CageDao {
    @Override
    public boolean save(Cage cage) throws Exception {
        return CrudUtil.execute("INSERT INTO Cage VALUES(?, ?, ?, ?)",
                cage.getCageId(),
                cage.getNoOfHens(),
                cage.getQtyOnFood(),
                cage.getQtyOnVitamin());
    }

    @Override
    public boolean update(Cage cage) throws Exception {
        return CrudUtil.execute("UPDATE Cage SET noOfHen = ?, qtyOnFood = ?," +
                        " qtyOnVitamin = ? WHERE cageId = ?", cage.getNoOfHens(), cage.getQtyOnFood(),
                cage.getQtyOnVitamin(), cage.getCageId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Cage search(String s) throws Exception {
        return null;
    }

    @Override
    public List<Cage> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Cage");

        ArrayList<Cage> cageList = new ArrayList<>();

        while(set.next()){
            cageList.add(new Cage(
                    set.getString(1),
                    set.getInt(2),
                    set.getString(3),
                    set.getString(4)
            ));
        }
        return cageList;
    }

    @Override
    public int getHensCount() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT noOfHen FROM Cage");

        int tot = 0;

        while(set.next()){
            tot += set.getInt(1);
        }
        return tot;
    }

    @Override
    public ArrayList<String> getCageNames() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT cageId FROM Cage");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()){
            list.add(set.getString(1));

        }
        return list;
    }

    @Override
    public String getCageId() throws Exception {
        /*ResultSet set = CrudUtil.execute("SELECT cageId FROM Cage ORDER BY cageId DESC LIMIT 1");

        String id = "CG001";
        if(set.next()){
            String tempId = set.getString(1);
            String []cs = tempId.split("CG");
            int selectedId = Integer.parseInt(cs[1]);
            if(selectedId < 10000){
                id = "CG00" + (selectedId + 1);
            }
        }
        return id;*/
        ResultSet set = CrudUtil.
                execute("SELECT cageId FROM Cage ORDER BY cageId DESC LIMIT 1");
        String code="CG001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("CG");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                code="CG00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                code="CG0"+(selectedId+1);
            }else if(selectedId >= 99){
                code="CG"+(selectedId+1);
            }
        }
        return code;

    }
}
