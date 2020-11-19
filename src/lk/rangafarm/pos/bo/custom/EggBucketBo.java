package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dao.custom.EggBucketDao;
import lk.rangafarm.pos.dto.EggBucketDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.entity.EggBucket;

import java.util.ArrayList;
import java.util.List;

public interface EggBucketBo extends SuperBo {
    public boolean saveEggBucket(EggBucketDto dto) throws Exception;

    public boolean UpdateEggBucket(EggBucketDto dto) throws Exception;

    public EggBucketDto getEggBucket(String id) throws Exception;

    public boolean deleteEggBucket(String id) throws Exception;

    public ArrayList<String> getCageNames() throws Exception;

    public ArrayList<String> getEmployeeId() throws Exception;

    public String getId() throws Exception;

    public EggBucketDto recentlyAdded() throws Exception;

    public int totalEggForToday(String date) throws Exception;

    public int sevenEgg() throws Exception;

    public int tenEgg() throws Exception;

    public int twelveEgg() throws Exception;

    public int twoEgg() throws Exception;

    public int fourEgg() throws Exception;

}
