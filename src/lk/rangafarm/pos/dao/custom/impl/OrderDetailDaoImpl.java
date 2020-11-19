package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.OrderDetailDao;
import lk.rangafarm.pos.entity.OrderDetail;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetail oD) throws Exception {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES(?, ?, ?)",
                oD.getOrderId(),
                oD.getProductId(),
                oD.getQty());
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() throws Exception {
        return null;
    }
}
