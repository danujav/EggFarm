package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.EggBucketDetailDao;
import lk.rangafarm.pos.entity.EggBucketDetail;

import java.util.List;

public class EggBucketDetailDaoImpl implements EggBucketDetailDao {
    @Override
    public boolean save(EggBucketDetail e) throws Exception {
        return CrudUtil.execute("INSERT INTO EggBucketDetail VALUES(?, ?, ?)", e.getEggBucketId(),
                e.getProductId(), e.getQty());
    }

    @Override
    public boolean update(EggBucketDetail eggBucketDetail) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public EggBucketDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public List<EggBucketDetail> getAll() throws Exception {
        return null;
    }
}
