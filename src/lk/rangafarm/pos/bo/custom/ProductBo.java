package lk.rangafarm.pos.bo.custom;

import lk.rangafarm.pos.bo.SuperBo;
import lk.rangafarm.pos.dto.OrderDetailDto;
import lk.rangafarm.pos.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public interface ProductBo extends SuperBo {
    public boolean saveProduct(ProductDto dto) throws Exception;

    public boolean UpdateProduct(ProductDto dto) throws Exception;

    public ProductDto getProduct(String id) throws Exception;

    public List<ProductDto> getAllProduct() throws Exception;

    public String getId() throws Exception;

    public ArrayList<String> getEggId() throws Exception;

    public List<ProductDto> getAllEgg() throws Exception;

    public int getEggCount() throws Exception;

    public ArrayList<String> getProductId() throws Exception;

    public boolean UpdateProduct(ArrayList<OrderDetailDto> dto) throws Exception;

    public boolean UpdateProduct(OrderDetailDto dto) throws Exception;

    public ArrayList<Integer> getOrderWiseEggSellingRate() throws Exception;

}
