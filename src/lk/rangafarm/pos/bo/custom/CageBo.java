package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.CageDto;
import lk.rangafarm.pos.dto.CustomerDto;

import java.util.List;

public interface CageBo extends SuperBo {
    public boolean saveCage(CageDto dto) throws Exception;

    public boolean UpdateCage(CageDto dto) throws Exception;

    public CageDto getEggBucket(String id) throws Exception;

    public List<CageDto> getAllCage() throws Exception;

    public String getId() throws Exception;

    public int getHensCount() throws Exception;

}
