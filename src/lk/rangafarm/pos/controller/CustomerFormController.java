package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.CustomerBo;
import lk.rangafarm.pos.bo.custom.impl.CustomerBoImpl;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.view.tm.CustomerTM;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {
    public TableView<CustomerTM> tblCustomer;
    public JFXTextField txtCustomerId;
    public JFXTextField txtShopName;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public TableColumn colCustomerId;
    public TableColumn colName ;
    public TableColumn colShopName;
    public TableColumn colAddress;
    public TableColumn colMobileNumber;
    public JFXTextField txtUpId;
    public JFXTextField txtUpName;
    public JFXTextField txtUpShopName;
    public JFXTextField txtUpAddress;
    public JFXTextField txtUpMobileNumber;

    CustomerBo bo;

    public void initialize(){
        bo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);
        loadId();
        loadAllCustomer();
        setCellValueFactory();
        tableListener();
    }

    private void tableListener(){
        tblCustomer.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(CustomerTM tm) {
        try {
            txtUpId.setText(tm.getCustId());
            txtUpName.setText(tm.getName());
            txtUpShopName.setText(tm.getShopName());
            txtUpAddress.setText(tm.getAddress());
            txtUpMobileNumber.setText(String.valueOf(tm.getMobileNumber()));
        }catch (Exception e){

        }
    }

    private void loadId(){
        try {
            String id = bo.getId();
            txtCustomerId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory("custId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colShopName.setCellValueFactory(new PropertyValueFactory("ShopName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory("MobileNumber"));
    }

    public void loadAllCustomer(){
        try {
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            List<CustomerDto> cust = bo.getAllCustomer();

            for(CustomerDto dto : cust){
                obList.add(new CustomerTM(
                        dto.getCustId(),
                        dto.getName(),
                        dto.getShopName(),
                        dto.getAddress(),
                        dto.getMobileNumber()
                ));
                tblCustomer.setItems(obList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddCustomerOnAction(ActionEvent event) {
        if(Pattern.compile("^(C)[0-9]{1,}$").matcher(txtCustomerId.getText()).matches()){
            if(Pattern.compile("^[A-z]{1,50}$").matcher(txtName.getText()).matches()){
                if(Pattern.compile("^[^@][A-z | () | -]{1,50}$").matcher(txtShopName.getText()).matches()){
                    if(Pattern.compile("^[^@][A-z|0-9|/|-]{1,}$").matcher(txtAddress.getText()).matches()){
                        if(Pattern.compile("^[0-9]{9,10}$").matcher(txtMobileNumber.getText()).matches()){
                            try {
                                if(bo.saveCustomer(new CustomerDto(
                                        txtCustomerId.getText(),
                                        txtName.getText(),
                                        txtShopName.getText(),
                                        txtAddress.getText(),
                                        Integer.parseInt(txtMobileNumber.getText())))){
                                    txtCustomerId.setText(null);
                                    txtName.setText(null);
                                    txtShopName.setText(null);
                                    txtAddress.setText(null);
                                    txtMobileNumber.setText(null);
                                    loadAllCustomer();
                                    new Alert(Alert.AlertType.INFORMATION,"Customer Added Success!").show();
                                }
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.WARNING,"Customer Not Added!").show();
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
                    txtShopName.setFocusColor(Paint.valueOf("Red"));
                    txtShopName.requestFocus();
                }
            }else{
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }

        }else{
                txtCustomerId.setFocusColor(Paint.valueOf("Red"));
                txtCustomerId.requestFocus();
        }
        loadId();
    }

    public void btnUpdateOnAction(ActionEvent event) {
          if(Pattern.compile("^(C)[0-9]{1,}$").matcher(txtUpId.getText()).matches()){
            if(Pattern.compile("^[A-z]{1,50}$").matcher(txtUpName.getText()).matches()){
                if(Pattern.compile("^[^@][A-z | () | -]{1,50}$").matcher(txtUpShopName.getText()).matches()){
                    if(Pattern.compile("^[^@][A-z|0-9|/|-]{1,}$").matcher(txtUpAddress.getText()).matches()){
                        if(Pattern.compile("^[0-9]{9,10}$").matcher(txtUpMobileNumber.getText()).matches()){
                            try {
                                if(bo.UpdateCustomer(new CustomerDto(
                                        txtUpId.getText(),
                                        txtUpName.getText(),
                                        txtUpShopName.getText(),
                                        txtUpAddress.getText(),
                                        Integer.parseInt(txtUpMobileNumber.getText())
                                ))){
                                    loadAllCustomer();
                                   // new Alert(Alert.AlertType.CONFIRMATION,"Customer has Updated!").show();
                                    txtUpId.setText(null);
                                    txtUpName.setText(null);
                                    txtUpShopName.setText(null);
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
                    txtUpShopName.setFocusColor(Paint.valueOf("Red"));
                    txtUpShopName.requestFocus();
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
