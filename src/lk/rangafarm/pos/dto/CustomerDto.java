package lk.rangafarm.pos.dto;

public class CustomerDto {
    private String custId;
    private String name;
    private String shopName;
    private String address;
    private int mobileNumber;

    public CustomerDto() {
    }

    public CustomerDto(String custId, String name, String shopName, String address, int mobileNumber) {
        this.setCustId(custId);
        this.setName(name);
        this.setShopName(shopName);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
