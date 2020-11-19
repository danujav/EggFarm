package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.OrderDetailDto;
import lk.rangafarm.pos.dto.SellingDto;

public interface SellingBo extends SuperBo {
    public boolean PlaceOrder(SellingDto dto) throws Exception;

}
