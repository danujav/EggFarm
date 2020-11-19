package lk.rangafarm.pos.entity;

public class Orders implements SuperEntity{
    private String orderId;
    private String custId;
    private String orderDate;
    private String time;

    public Orders() {
    }

    public Orders(String orderId, String custId, String orderDate, String time) {
        this.setOrderId(orderId);
        this.setCustId(custId);
        this.setOrderDate(orderDate);
        this.setTime(time);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
