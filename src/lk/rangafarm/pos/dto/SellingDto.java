package lk.rangafarm.pos.dto;

import java.util.ArrayList;

public class SellingDto {
    private String orderId;
    private String custId;
    private String date;
    private String time;
    private ArrayList<OrderDetailDto> list;

    public SellingDto() {
    }

    public SellingDto(String orderId, String custId, String date, String time, ArrayList<OrderDetailDto> list) {
        this.setOrderId(orderId);
        this.setCustId(custId);
        this.setDate(date);
        this.setTime(time);
        this.setList(list);
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

    public ArrayList<OrderDetailDto> getList() {
        return list;
    }

    public void setList(ArrayList<OrderDetailDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SellingDto{" +
                "orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", list=" + list +
                '}';
    }
}
