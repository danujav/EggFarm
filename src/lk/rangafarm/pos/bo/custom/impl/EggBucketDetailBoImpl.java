package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.EggBucketDetailBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.custom.EggBucketDetailDao;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.EggBucketDetailDto;
import lk.rangafarm.pos.entity.EggBucketDetail;

import java.util.List;

public class EggBucketDetailBoImpl implements EggBucketDetailBo {
    EggBucketDetailDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EGGBUCKETDETAIL);

    @Override
    public boolean saveBucketDetail(EggBucketDetailDto dto) throws Exception {
        return dao.save(new EggBucketDetail(
                dto.getEggBucketId(),
                dto.getProductId(),
                dto.getQty()
        ));
    }

    @Override
    public boolean UpdateBucketDetail(CustomerDto dto) throws Exception {
        return false;
    }

    @Override
    public EggBucketDetailDto getBucketDetail(String id) throws Exception {
        return null;
    }

    @Override
    public List<EggBucketDetailDto> getAllBucketDetail() throws Exception {
        return null;
    }
}
