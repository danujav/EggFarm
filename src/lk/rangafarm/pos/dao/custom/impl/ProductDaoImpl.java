package lk.rangafarm.pos.dao.custom.impl;

import lk.rangafarm.pos.dao.CrudUtil;
import lk.rangafarm.pos.dao.custom.ProductDao;
import lk.rangafarm.pos.entity.Customer;
import lk.rangafarm.pos.entity.Product;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(Product product) throws Exception {
        return CrudUtil.execute("INSERT INTO Product VALUES(?, ?, ?, ?)",
                product.getProductId(), product.getDescription(),
                product.getUnitPrice(), product.getQtyOnHand());
    }

    @Override
    public boolean update(Product p) throws Exception {
        return CrudUtil.execute("UPDATE Product SET description = ?, unitPrice = ?," +
                " qtyOnHand = ? WHERE productId = ?", p.getDescription(),
                p.getUnitPrice(), p.getQtyOnHand(), p.getProductId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Product search(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Product WHERE productId = ?", s);

        while(set.next()){
            return new Product(set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4));
        }
        return null;
    }

    @Override
    public List<Product> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean updateProductQty(Product p) throws Exception {
        return CrudUtil.execute("UPDATE Product SET qtyOnHand = qtyOnHand - ? WHERE productId = ?",
                p.getQtyOnHand(), p.getProductId());
    }

    @Override
    public ArrayList<Integer> getOrderWiseEgg() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT qty FROM OrderDetail WHERE productId LIKE 'EG%'");
        ArrayList<Integer> qtyList = new ArrayList();

        while(set.next()){
            qtyList.add(set.getInt(1));
        }
        return qtyList;

    }

    @Override
    public ArrayList<String> getProductId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT productId FROM Product");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()){
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public int getEggCount() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT qtyOnHand FROM Product");

        int tot = 0;

        while(set.next()){
            tot += set.getInt(1);
        }
        return tot;
    }

    @Override
    public ArrayList<String> getEggId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT productId FROM Product WHERE productId LIKE 'EG%'");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()){
            list.add(set.getString(1));

        }
        return list;
    }

    @Override
    public List<Product> getAllFood() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Product WHERE productId LIKE 'F%'");
        ArrayList<Product> foods = new ArrayList<>();
        while(set.next()){
            foods.add(new Product(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)

            ));
        }
        return foods;
    }

    @Override
    public List<Product> getAllEgg() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Product WHERE productId LIKE 'EG%'");
        ArrayList<Product> foods = new ArrayList<>();
        while(set.next()){
            foods.add(new Product(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)

            ));
        }
        return foods;
    }

    @Override
    public String getFoodId() throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT productId FROM Product ORDER BY productId DESC LIMIT 1");
        String code="F001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("F");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                code="F00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                code="F0"+(selectedId+1);
            }else if(selectedId >= 99){
                code="F"+(selectedId+1);
            }
        }
        return code;
    }

}
