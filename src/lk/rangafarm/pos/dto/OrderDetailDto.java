package lk.rangafarm.pos.dto;

public class OrderDetailDto {
    private String orderId;
    private String productId;
    private String description;
    private double unitPrice;
    private int qty;

    public OrderDetailDto() {
    }

    public OrderDetailDto(String orderId, String productId, String description, double unitPrice, int qty) {
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                '}';
    }
}
