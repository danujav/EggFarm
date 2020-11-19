package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.OrderDto;

public interface OrderBo extends SuperBo {
    public String getNextOrderId() throws Exception;

    public boolean addOrder(OrderDto dto) throws Exception;
}
