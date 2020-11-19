package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.OrderDetailBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.custom.OrderDetailDao;
import lk.rangafarm.pos.dto.OrderDetailDto;
import lk.rangafarm.pos.entity.OrderDetail;

import java.util.ArrayList;

public class OrderDetailBoImpl implements OrderDetailBo {
    OrderDetailDao oDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDERDETAIL);

    @Override
    public boolean addOrderDetail(ArrayList<OrderDetailDto> dto) throws Exception {
        for(OrderDetailDto dt : dto){
            boolean b = addOrderDetail(dt);
            if(!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addOrderDetail(OrderDetailDto dto) throws Exception {
        return oDao.save(new OrderDetail(
                dto.getOrderId(),
                dto.getProductId(),
                dto.getQty()
        ));
    }
}
