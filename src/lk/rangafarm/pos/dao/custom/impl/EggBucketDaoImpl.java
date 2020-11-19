package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.EggBucketDao;
import lk.rangafarm.pos.entity.EggBucket;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EggBucketDaoImpl implements EggBucketDao {
    @Override
    public boolean save(EggBucket e) throws Exception {
        return CrudUtil.execute("INSERT INTO EggBucket VALUES(?, ?, ?, ?, ?, ?)",
                e.getEggBucketId(),
                e.getCageId(),
                e.getEmpId(),
                e.getDates(),
                e.getTimes(),
                e.getQty());
    }

    @Override
    public boolean update(EggBucket eggBucket) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public EggBucket search(String s) throws Exception {
        return null;
    }

    @Override
    public List<EggBucket> getAll() throws Exception {
        return null;
    }

    @Override
    public int totalEggForToday(String id) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket WHERE dates = ? ", id);
        int tot = 0;
        while(set.next()){
            tot += set.getInt(6);
        }
        return tot;
    }

    @Override
    public int sevenEgg() throws Exception {
        String s = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket WHERE times = '7:30 am' && dates = ?", s);

        int tot = 0;
        while (set.next()){
            tot += set.getInt(6);
        }
        return tot;
    }

    @Override
    public int tenEgg() throws Exception {
        String s = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket WHERE times = '10:30 am' && dates = ?", s);
        int tot = 0;
        while (set.next()){
            tot += set.getInt(6);

        }
        return tot;
    }

    @Override
    public int twelveEgg() throws Exception {
        String s = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket WHERE times = '12:30 am' && dates = ?", s);
        int tot = 0;
        while (set.next()){
            tot += set.getInt(6);

        }
        return tot;
    }

    @Override
    public int twoEgg() throws Exception {
        String s = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket WHERE times = '2:30 am' && dates = ?", s);
        int tot = 0;
        while (set.next()){
            tot += set.getInt(6);

        }
        return tot;
    }

    @Override
    public int fourEgg() throws Exception {
        String s = LocalDate.now().toString();
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket WHERE times = '4:30 am' && dates = ?", s);
        int tot = 0;
        while (set.next()){
            tot += set.getInt(6);

        }
        return tot;
    }

    @Override
    public EggBucket recentlyAdded() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM EggBucket ORDER BY eggBucketId DESC LIMIT 1");
        while(set.next()){
            return new EggBucket(set.getString(1), set.getString(2),
                    set.getString(3), set.getString(4),
                    set.getString(5), set.getInt(6));
        }
        return null;
    }

    @Override
    public String getBucketId() throws Exception {
       /* ResultSet set = CrudUtil.execute("SELECT eggBucketId FROM EggBucket ORDER BY eggBucketId DESC LIMIT 1");

        String id = "EB001";
        if(set.next()){
            String tempId = set.getString(1);
            String []cs = tempId.split("EB");
            int selectedId = Integer.parseInt(cs[1]);
            if(selectedId < 10000){
                id = "EB00" + (selectedId + 1);
            }
        }
        return id;*/

        ResultSet set = CrudUtil.
                execute("SELECT eggBucketId FROM EggBucket ORDER BY eggBucketId DESC LIMIT 1");
        String code="EB001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("EB");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                code="EB00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                code="EB0"+(selectedId+1);
            }else if(selectedId >= 99){
                code="EB"+(selectedId+1);
            }
        }
        return code;
    }


}
