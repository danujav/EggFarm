package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.OrderBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.OrderDao;
import lk.rangafarm.pos.dto.OrderDto;
import lk.rangafarm.pos.entity.Orders;

public class OrderBoImpl implements OrderBo {
    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);
    OrderDao oDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ORDER);

    @Override
    public String getNextOrderId() throws Exception {
        return oDao.getOrderId();
    }

    @Override
    public boolean addOrder(OrderDto dto) throws Exception {
        return oDao.save(new Orders(
                dto.getOrderId(),
                dto.getCustId(),
                dto.getDate(),
                dto.getTime()
        ));
    }
}
