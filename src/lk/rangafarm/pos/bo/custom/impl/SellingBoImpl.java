package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.OrderBo;
import lk.rangafarm.pos.bo.custom.OrderDetailBo;
import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.bo.custom.SellingBo;
import lk.rangafarm.pos.db.DBConnection;
import lk.rangafarm.pos.dto.OrderDetailDto;
import lk.rangafarm.pos.dto.OrderDto;
import lk.rangafarm.pos.dto.SellingDto;

public class SellingBoImpl implements SellingBo {
    OrderBo oBo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER);
    OrderDetailBo orderDetailBo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDERDETAIL);
    ProductBo pBo = BoFactory.getInstance().getBo(BoFactory.BoType.PRODUCT);


    @Override
    public boolean PlaceOrder(SellingDto dto) throws Exception {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            if (oBo.addOrder(new OrderDto(
                    dto.getOrderId(),
                    dto.getCustId(),
                    dto.getDate(),
                    dto.getTime()))) {
                if (orderDetailBo.addOrderDetail(dto.getList())) {
                    if (pBo.UpdateProduct(dto.getList())) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
