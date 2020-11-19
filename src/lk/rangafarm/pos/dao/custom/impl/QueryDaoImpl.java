package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.entity.Attendance;
import lk.rangafarm.pos.entity.EggBucket;
import lk.rangafarm.pos.entity.LineChart;
import lk.rangafarm.pos.entity.Product;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<Integer> setLineChart() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT productId, SUM(qty) FROM Orders INNER JOIN OrderDetail ON Orders.orderId = OrderDetail.orderId WHERE productId LIKE 'EG%' && " +
                "MONTH(orderDate) = MONTH(CURDATE()) GROUP BY productId ORDER BY productId ASC");

        ArrayList<Integer> list = new ArrayList<>();

        while(set.next()){
            list.add(set.getInt(2));
        }
        return list;
    }
}
