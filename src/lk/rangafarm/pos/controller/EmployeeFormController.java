package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.EmployeeBo;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.entity.Employee;
import lk.rangafarm.pos.view.tm.CustomerTM;
import lk.rangafarm.pos.view.tm.EmployeeTM;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public TableView<EmployeeTM> tblEmployee;
    public JFXTextField txtEmpId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtUpId;
    public JFXTextField txtUpName;
    public JFXTextField txtUpAddress;
    public JFXTextField txtUpMobileNumber;
    public TableColumn colEmpId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public TableColumn colOperate;

    EmployeeBo bo;

    public void initialize(){
        bo = BoFactory.getInstance().getBo(BoFactory.BoType.EMPLOYEE);
        loadAllEmployee();
        getId();
        setCellValueFactory();
        tableListener();
    }

    public void getId(){
        try {
            String id = bo.getId();
            txtEmpId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory(){
        colEmpId.setCellValueFactory(new PropertyValueFactory("empId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory("MobileNumber"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    private void tableListener(){
        tblEmployee.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(EmployeeTM tm) {
        try {
            txtUpId.setText(tm.getEmpId());
            txtUpName.setText(tm.getName());
            txtUpAddress.setText(tm.getAddress());
            txtUpMobileNumber.setText(String.valueOf(tm.getMobileNumber()));
        }catch (Exception e){

        }
    }

    public void loadAllEmployee(){
        try {
            ObservableList<EmployeeTM> tmList = FXCollections.observableArrayList();
            List<EmployeeDto> all = bo.getAllEmployee();
            for(EmployeeDto dto : all){
                Button btn = new Button("Delete");
               EmployeeTM tm = new EmployeeTM(
                       dto.getEmpId(),
                       dto.getName(),
                       dto.getAddress(),
                       dto.getMobileNumber(),
                       btn
               );
               tmList.add(tm);
                btn.setOnAction((e) -> {
                    try {

                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (bo.deleteEmployee(tm.getEmpId())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllEmployee();
                                getId();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        } else {
                            //no
                        }


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblEmployee.setItems(tmList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"oops! cannot load data to the table").show();
        }
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
        if(Pattern.compile("^(E00)[1-9]{1,50}$").matcher(txtEmpId.getText()).matches()){
            if(Pattern.compile("^[A-z]{1,50}$").matcher(txtName.getText()).matches()){
                    if(Pattern.compile("^[^@][A-z|0-9|/|-]{1,}$").matcher(txtAddress.getText()).matches()){
                        if(Pattern.compile("^[0-9]{9,10}$").matcher(txtMobileNumber.getText()).matches()){
                            try {
                                if(bo.saveEmployee(new EmployeeDto(
                                        txtEmpId.getText(),
                                        txtName.getText(),
                                        txtAddress.getText(),
                                        Integer.parseInt(txtMobileNumber.getText())))){
                                    txtEmpId.setText(null);
                                    txtName.setText(null);
                                    txtAddress.setText(null);
                                    txtMobileNumber.setText(null);
                                    new Alert(Alert.AlertType.CONFIRMATION,"Employee Added Success!").show();
                                    loadAllEmployee();
                                }
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong!").show();
                            }
                        }else{
                            txtMobileNumber.setFocusColor(Paint.valueOf("Red"));
                            txtMobileNumber.requestFocus();
                        }
                    }else{
                        txtAddress.setFocusColor(Paint.valueOf("Red"));
                        txtAddress.requestFocus();
                    }

            }else{
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }

        }else{
            txtEmpId.setFocusColor(Paint.valueOf("Red"));
            txtEmpId.requestFocus();
        }
        getId();

    }

    public void btnUpdateOnAction(ActionEvent event) {
        if(Pattern.compile("^(E00)[1-9]{1,50}$").matcher(txtUpId.getText()).matches()){
            if(Pattern.compile("^[A-z]{1,50}$").matcher(txtUpName.getText()).matches()){
                    if(Pattern.compile("^[^@][A-z|0-9|/|-]{1,}$").matcher(txtUpAddress.getText()).matches()){
                        if(Pattern.compile("^[0-9]{9,10}$").matcher(txtUpMobileNumber.getText()).matches()){
                            try {
                                if(bo.UpdateEmployee(new EmployeeDto(
                                        txtUpId.getText(),
                                        txtUpName.getText(),
                                        txtUpAddress.getText(),
                                        Integer.parseInt(txtUpMobileNumber.getText())
                                ))){
                                    loadAllEmployee();
                                    new Alert(Alert.AlertType.INFORMATION,"Employee has Updated!").show();
                                    txtUpId.setText(null);
                                    txtUpName.setText(null);
                                    txtUpAddress.setText(null);
                                    txtUpMobileNumber.setText(null);
                                }
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.WARNING,"Something went Wrong !").show();
                            }
                        }else{
                            txtUpMobileNumber.setFocusColor(Paint.valueOf("Red"));
                            txtUpMobileNumber.requestFocus();
                        }
                    }else{
                        txtUpAddress.setFocusColor(Paint.valueOf("Red"));
                        txtUpAddress.requestFocus();
                    }
            }else{
                txtUpName.setFocusColor(Paint.valueOf("Red"));
                txtUpName.requestFocus();
            }

        }else{
            txtUpId.setFocusColor(Paint.valueOf("Red"));
            txtUpId.requestFocus();
        }
    }
}
