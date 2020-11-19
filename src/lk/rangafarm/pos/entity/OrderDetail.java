package lk.rangafarm.pos.entity;

public class OrderDetail implements SuperEntity{
    private String orderId;
    private String productId;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String productId, int qty) {
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setQty(qty);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
