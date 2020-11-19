package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.EggBucket;

import java.util.ArrayList;

public interface EggBucketDao extends CrudDao<EggBucket, String> {
    public int totalEggForToday(String date) throws Exception;

    public int sevenEgg() throws Exception;

    public int tenEgg() throws Exception;

    public int twelveEgg() throws Exception;

    public int twoEgg() throws Exception;

    public int fourEgg() throws Exception;

    public EggBucket recentlyAdded() throws Exception;

    public String getBucketId() throws Exception;


}
