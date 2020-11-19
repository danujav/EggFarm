package lk.rangafarm.pos.dto;

public class OrderDto {
    private String orderId;
    private String custId;
    private String date;
    private String time;

    public OrderDto() {
    }

    public OrderDto(String orderId, String custId, String date, String time) {
        this.setOrderId(orderId);
        this.setCustId(custId);
        this.setDate(date);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
