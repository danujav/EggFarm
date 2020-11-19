package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.EggBucketBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.CageDao;
import lk.rangafarm.pos.dao.custom.EggBucketDao;
import lk.rangafarm.pos.dao.custom.EmployeeDao;
import lk.rangafarm.pos.dto.EggBucketDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.entity.EggBucket;

import java.util.ArrayList;
import java.util.List;

public class EggBucketBoImpl implements EggBucketBo {

    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);
    EggBucketDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EGGBUCKET);
    EmployeeDao eDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);
    CageDao cDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAGE);

    @Override
    public boolean saveEggBucket(EggBucketDto dto) throws Exception {
        return dao.save(new EggBucket(
                dto.getEggBucketId(),
                dto.getCageId(),
                dto.getEmpId(),
                dto.getDates(),
                dto.getTimes(),
                dto.getQty()
        ));
    }

    @Override
    public boolean UpdateEggBucket(EggBucketDto dto) throws Exception {
        return false;
    }

    @Override
    public EggBucketDto getEggBucket(String id) throws Exception {
        return null;
    }

    @Override
    public boolean deleteEggBucket(String id) throws Exception {
        return false;
    }

    @Override
    public ArrayList<String> getCageNames() throws Exception {
        return cDao.getCageNames();
    }

    @Override
    public ArrayList<String> getEmployeeId() throws Exception {
        return eDao.getEmployeeId();
    }

    @Override
    public String getId() throws Exception {
        return dao.getBucketId();
    }

    @Override
    public EggBucketDto recentlyAdded() throws Exception {
        EggBucket e = dao.recentlyAdded();
        return new EggBucketDto(e.getEggBucketId(), e.getCageId(), e.getEmpId(), e.getDates(), e.getTimes(), e.getQty());
    }

    @Override
    public int totalEggForToday(String date) throws Exception {
        return dao.totalEggForToday(date);

    }

    @Override
    public int sevenEgg() throws Exception {
        return dao.sevenEgg();
    }

    @Override
    public int tenEgg() throws Exception {
        return dao.tenEgg();
    }

    @Override
    public int twelveEgg() throws Exception {
        return dao.twelveEgg();
    }

    @Override
    public int twoEgg() throws Exception {
        return dao.twoEgg();
    }

    @Override
    public int fourEgg() throws Exception {
        return dao.fourEgg();
    }



}
