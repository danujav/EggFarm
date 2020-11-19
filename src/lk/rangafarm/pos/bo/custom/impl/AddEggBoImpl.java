package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.AddEggBo;
import lk.rangafarm.pos.bo.custom.EggBucketDetailBo;
import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.db.DBConnection;
import lk.rangafarm.pos.dto.EggBucketDetailDto;
import lk.rangafarm.pos.dto.ProductDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddEggBoImpl implements AddEggBo {
    ProductBo pBo = BoFactory.getInstance().getBo(BoFactory.BoType.PRODUCT);
    EggBucketDetailBo eBo = BoFactory.getInstance().getBo(BoFactory.BoType.EGGBUCKETDETAIL);

    @Override
    public boolean addEgg(ProductDto dto, EggBucketDetailDto eDto) throws Exception {
           try {
               DBConnection.getInstance().getConnection().setAutoCommit(false);
               if (pBo.UpdateProduct(
                       new ProductDto(
                               dto.getProductId(),
                               dto.getDescription(),
                               dto.getUnitPrice(),
                               dto.getQtyOnHand()
                       ))) {
                   if (eBo.saveBucketDetail(
                           new EggBucketDetailDto(
                                   eDto.getEggBucketId(),
                                   eDto.getProductId(),
                                   eDto.getQty()
                           ))) {
                       DBConnection.getInstance().getConnection().commit();
                       return true;
                   }
               }
               DBConnection.getInstance().getConnection().rollback();
               return false;
           }finally {
               DBConnection.getInstance().getConnection().setAutoCommit(true);
           }

    }


}
