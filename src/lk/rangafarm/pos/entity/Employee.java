package lk.rangafarm.pos.entity;

public class Employee implements SuperEntity{
    private String empId;
    private String name;
    private String address;
    private int mobileNumber;

    public Employee() {
    }

    public Employee(String empId, String name, String address, int mobileNumber) {
        this.setEmpId(empId);
        this.setName(name);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
