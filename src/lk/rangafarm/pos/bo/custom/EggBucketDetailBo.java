package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.EggBucketDetailDto;

import java.util.List;

public interface EggBucketDetailBo extends SuperBo {
    public boolean saveBucketDetail(EggBucketDetailDto dto) throws Exception;

    public boolean UpdateBucketDetail(CustomerDto dto) throws Exception;

    public EggBucketDetailDto getBucketDetail(String id) throws Exception;

    public List<EggBucketDetailDto> getAllBucketDetail() throws Exception;

}
