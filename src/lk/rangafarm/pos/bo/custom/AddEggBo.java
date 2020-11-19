package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.EggBucketDetailDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.dto.ProductDto;

import java.util.ArrayList;


public interface AddEggBo extends SuperBo {
    public boolean addEgg(ProductDto dto, EggBucketDetailDto eDto) throws Exception;

}
