package lk.rangafarm.pos.view.tm;

import javafx.scene.control.Button;
import lk.rangafarm.pos.entity.SuperEntity;

public class PlaceOrderTM implements SuperEntity {
    private String productId;
    private String description;
    private double unitPrice;
    private int qty;
    private double totalPrice;
    private Button btn;


    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String productId, String description, double unitPrice, int qty, double totalPrice, Button btn) {
        this.setProductId(productId);
        this.setDescription(description);
        this.setUnitPrice(unitPrice);
        this.setQty(qty);
        this.setTotalPrice(totalPrice);
        this.setBtn(btn);
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "productId='" + productId + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", totalPrice=" + totalPrice +
                ", btn=" + btn +
                '}';
    }
}
