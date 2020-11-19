package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.Orders;

public interface OrderDao extends CrudDao<Orders, String> {
    public String getOrderId() throws Exception;
}
