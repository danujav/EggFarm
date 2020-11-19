package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.OrderDetailDto;

import java.util.ArrayList;

public interface OrderDetailBo extends SuperBo {
    public boolean addOrderDetail(ArrayList<OrderDetailDto> dto) throws Exception;

    public boolean addOrderDetail(OrderDetailDto dto) throws Exception;


}
