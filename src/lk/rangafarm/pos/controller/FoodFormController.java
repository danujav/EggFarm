package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.ProductDto;
import lk.rangafarm.pos.entity.Product;
import lk.rangafarm.pos.view.tm.CustomerTM;
import lk.rangafarm.pos.view.tm.FoodTM;

import java.util.List;
import java.util.regex.Pattern;

public class FoodFormController {
    public TableView<FoodTM> tblFood;
    public JFXTextField txtFoodId;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUpFoodQty;
    public JFXComboBox cmbUpFoodId;
    public JFXTextField txtUpDescription;
    public JFXTextField txtUpUnitPrice;
    public JFXTextField txtFoodCode;
    public JFXTextField txtUpFoodCode;
    public TableColumn colProductId;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;

    ProductBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.PRODUCT);

    public void initialize(){
        loadId();
        tableListener();
        loadAllFood();
        setCellValueFactory();
    }

    private void setCellValueFactory(){
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
    }

    public void loadAllFood(){
        try {
            ObservableList<FoodTM> obList = FXCollections.observableArrayList();
            List<ProductDto> cust = bo.getAllProduct();

            for(ProductDto dto : cust){
                obList.add(new FoodTM(
                        dto.getProductId(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQtyOnHand()
                ));
                tblFood.setItems(obList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadId(){
        try {
            String id = bo.getId();
            txtFoodId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableListener(){
        tblFood.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(FoodTM tm) {
        try {
            txtUpFoodCode.setText(tm.getProductId());
            txtUpDescription.setText(tm.getDescription());
            txtUpUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
            txtUpFoodQty.setText(String.valueOf(tm.getQtyOnHand()));
        }catch (Exception e){

        }
    }

    public void btnAddStoreOnAction(ActionEvent event) {
        if(Pattern.compile("^(F)[0-9]{1,50}$").matcher(txtFoodId.getText()).matches()){
            if(Pattern.compile("^[A-z | 0-9 | -]{1,50}$").matcher(txtDescription.getText()).matches()){
                if(Pattern.compile("^[0-9 | .]{1,}$").matcher(txtUnitPrice.getText()).matches()){
                    if(Pattern.compile("^[0-9]{1,}$").matcher(txtQtyOnHand.getText()).matches()){
                            try {
                                if(bo.saveProduct(new ProductDto(
                                        txtFoodId.getText(),
                                        txtDescription.getText(),
                                        Double.parseDouble(txtUnitPrice.getText()),
                                        Integer.parseInt(txtQtyOnHand.getText())))){
                                    new Alert(Alert.AlertType.CONFIRMATION,"Food Added!").show();
                                    loadId();
                                    loadAllFood();
                                    txtDescription.setText(null);
                                    txtUnitPrice.setText(null);
                                    txtQtyOnHand.setText(null);
                                }
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.WARNING,"Food Not Added!").show();
                            }

                    }else{
                        txtQtyOnHand.setFocusColor(Paint.valueOf("Red"));
                        txtQtyOnHand.requestFocus();
                    }
                }else{
                    txtUnitPrice.setFocusColor(Paint.valueOf("Red"));
                    txtUnitPrice.requestFocus();
                }
            }else{
                txtDescription.setFocusColor(Paint.valueOf("Red"));
                txtDescription.requestFocus();
            }

        }else{
            txtFoodId.setFocusColor(Paint.valueOf("Red"));
            txtFoodId.requestFocus();
        }
        //loadId();

    }

    public void btnUpdateOnAction(ActionEvent event) {
        if(Pattern.compile("^(F)[0-9]{1,50}$").matcher(txtUpFoodCode.getText()).matches()){
            if(Pattern.compile("^[A-z | 0-9 | -]{1,50}$").matcher(txtUpDescription.getText()).matches()){
                if(Pattern.compile("^[0-9 | .]+$").matcher(txtUpUnitPrice.getText()).matches()){
                    if(Pattern.compile("^[0-9]+$").matcher(txtUpFoodQty.getText()).matches()){
                            try {
                                if(bo.UpdateProduct(new ProductDto(
                                        txtUpFoodCode.getText(),
                                        txtUpDescription.getText(),
                                        Double.parseDouble(txtUpUnitPrice.getText()),
                                        Integer.parseInt(txtUpFoodQty.getText())
                                ))){
                                    loadAllFood();
                                    loadId();
                                    new Alert(Alert.AlertType.CONFIRMATION,"Food has Updated!").show();

                                    txtUpDescription.setText(null);
                                    txtUpUnitPrice.setText(null);
                                    txtUpFoodQty.setText(null);
                                }
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.WARNING,"Something went Wrong !").show();
                            }
                    }else{
                        txtUpFoodQty.setFocusColor(Paint.valueOf("Red"));
                        txtUpFoodQty.requestFocus();
                    }
                }else{
                    txtUpUnitPrice.setFocusColor(Paint.valueOf("Red"));
                    txtUpUnitPrice.requestFocus();
                }
            }else{
                txtUpDescription.setFocusColor(Paint.valueOf("Red"));
                txtUpDescription.requestFocus();
            }

        }else{
            txtUpFoodCode.setFocusColor(Paint.valueOf("Red"));
            txtUpFoodCode.requestFocus();
        }
    }
}
