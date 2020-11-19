package lk.rangafarm.pos.view.tm;
import javafx.scene.control.Button;

public class EmployeeTM {
    private String empId;
    private String name;
    private String address;
    private int mobileNumber;
    private Button btn;

    public EmployeeTM() {
    }

    public EmployeeTM(String empId, String name, String address, int mobileNumber, Button btn) {
        this.setEmpId(empId);
        this.setName(name);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
        this.setBtn(btn);
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", btn=" + btn +
                '}';
    }
}
