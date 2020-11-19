package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.OrderDao;
import lk.rangafarm.pos.entity.Orders;

import java.sql.ResultSet;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Orders o) throws Exception {
        return CrudUtil.execute("INSERT INTO Orders VALUES (?, ?, ?, ?)",
                o.getOrderId(),
                o.getCustId(),
                o.getOrderDate(),
                o.getTime());
    }

    @Override
    public boolean update(Orders orders) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Orders search(String s) throws Exception {
        return null;
    }

    @Override
    public List<Orders> getAll() throws Exception {
        return null;
    }

    @Override
    public String getOrderId() throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");
        String code="O001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("O");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                code="O00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                code="O0"+(selectedId+1);
            }else if(selectedId >= 99){
                code="O"+(selectedId+1);
            }
        }
        return code;
    }
}
