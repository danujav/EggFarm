package lk.rangafarm.pos.bo.custom.impl;

import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.dao.DaoFactory;
import lk.rangafarm.pos.dao.QueryDao;
import lk.rangafarm.pos.dao.custom.EggBucketDetailDao;
import lk.rangafarm.pos.dao.custom.ProductDao;
import lk.rangafarm.pos.db.DBConnection;
import lk.rangafarm.pos.dto.OrderDetailDto;
import lk.rangafarm.pos.dto.ProductDto;
import lk.rangafarm.pos.entity.EggBucketDetail;
import lk.rangafarm.pos.entity.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {
    ProductDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PRODUCT);
    EggBucketDetailDao edao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.EGGBUCKETDETAIL);
    QueryDao qDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public boolean saveProduct(ProductDto dto) throws Exception {
        return dao.save(new Product(
                dto.getProductId(),
                dto.getDescription(),
                dto.getUnitPrice(),
                dto.getQtyOnHand()
        ));
    }

    @Override
    public boolean UpdateProduct(ProductDto dto) throws Exception {
        return dao.update(new Product(
                dto.getProductId(),
                dto.getDescription(),
                dto.getUnitPrice(),
                dto.getQtyOnHand()
        ));

    }

    @Override
    public ProductDto getProduct(String id) throws Exception {
        Product search = dao.search(id);
        return new ProductDto(
                search.getProductId(),
                search.getDescription(),
                search.getUnitPrice(),
                search.getQtyOnHand()
        );
    }

    @Override
    public List<ProductDto> getAllProduct() throws Exception {
        List<Product> all = dao.getAllFood();
        ArrayList<ProductDto> dtoList = new ArrayList<>();
        for(Product c : all){
            dtoList.add(new ProductDto(
                    c.getProductId(),
                    c.getDescription(),
                    c.getUnitPrice(),
                    c.getQtyOnHand()
            ));
        }
        return dtoList;
    }

    @Override
    public String getId() throws Exception {
        return dao.getFoodId();
    }

    @Override
    public ArrayList<String> getEggId() throws Exception {
        return dao.getEggId();
    }

    @Override
    public List<ProductDto> getAllEgg() throws Exception {
        List<Product> all = dao.getAllEgg();
        ArrayList<ProductDto> list = new ArrayList<>();

        for(Product p : all){
            list.add(new ProductDto(
                    p.getProductId(),
                    p.getDescription(),
                    p.getUnitPrice(),
                    p.getQtyOnHand()
            ));
        }
        return list;
    }

    @Override
    public int getEggCount() throws Exception {
        return dao.getEggCount();
    }

    @Override
    public ArrayList<String> getProductId() throws Exception {
        return dao.getProductId();
    }

    @Override
    public boolean UpdateProduct(ArrayList<OrderDetailDto> dto) throws Exception {
        for (OrderDetailDto dt : dto){
            boolean b = UpdateProduct(dt);
            if(!b){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean UpdateProduct(OrderDetailDto dto) throws Exception {
        return dao.updateProductQty(new Product(
                dto.getProductId(),
                dto.getDescription(),
                dto.getUnitPrice(),
                dto.getQty()));
    }

    @Override
    public ArrayList<Integer> getOrderWiseEggSellingRate() throws Exception {
        return qDao.setLineChart();
    }


}
