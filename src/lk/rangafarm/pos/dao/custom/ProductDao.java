package lk.rangafarm.pos.dao.custom;

import lk.rangafarm.pos.dao.CrudDao;
import lk.rangafarm.pos.entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductDao extends CrudDao<Product, String> {
    public boolean updateProductQty(Product p) throws Exception;

    public ArrayList<Integer> getOrderWiseEgg() throws Exception;

    public ArrayList<String> getProductId() throws Exception;

    public int getEggCount() throws Exception;

    public ArrayList<String> getEggId() throws Exception;

    public List<Product> getAllFood() throws Exception;

    public List<Product> getAllEgg() throws Exception;

    public String getFoodId() throws Exception;
}
