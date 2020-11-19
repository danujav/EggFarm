package lk.rangafarm.pos.entity;

public class LineChart {
    private String productId;
    private int qty;

    public LineChart() {
    }

    public LineChart(String productId, int qty) {
        this.setProductId(productId);
        this.setQty(qty);
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
