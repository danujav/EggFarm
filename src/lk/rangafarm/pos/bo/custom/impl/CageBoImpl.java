package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.CageBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.CageDao;
import lk.rangafarm.pos.dto.CageDto;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.entity.Cage;
import lk.rangafarm.pos.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CageBoImpl implements CageBo {
    CageDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAGE);
    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public boolean saveCage(CageDto dto) throws Exception {
        return dao.save(new Cage(
                dto.getCageId(),
                dto.getNoOfHens(),
                dto.getQtyOnFood(),
                dto.getQtyOnVitamin()
        ));
    }

    @Override
    public boolean UpdateCage(CageDto dto) throws Exception {
        return dao.update(new Cage(
                dto.getCageId(),
                dto.getNoOfHens(),
                dto.getQtyOnFood(),
                dto.getQtyOnVitamin()
        ));
    }

    @Override
    public CageDto getEggBucket(String id) throws Exception {
        return null;
    }
    @Override
    public List<CageDto> getAllCage() throws Exception {
        List<Cage> all = dao.getAll();
        ArrayList<CageDto> dtoList = new ArrayList<>();
        for(Cage c : all){
            dtoList.add(new CageDto(
                    c.getCageId(),
                    c.getNoOfHens(),
                    c.getQtyOnFood(),
                    c.getQtyOnVitamin()

            ));
        }
        return dtoList;
    }

    @Override
    public String getId() throws Exception {
        return dao.getCageId();
    }

    @Override
    public int getHensCount() throws Exception {
        return dao.getHensCount();
    }
}
