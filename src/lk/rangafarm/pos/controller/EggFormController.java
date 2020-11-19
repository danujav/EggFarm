package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.util.FXLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderImage;
import javafx.scene.paint.Paint;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.AddEggBo;
import lk.rangafarm.pos.bo.custom.EggBucketDetailBo;
import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.dao.custom.ProductDao;
import lk.rangafarm.pos.dto.*;
import lk.rangafarm.pos.entity.EggBucketDetail;
import lk.rangafarm.pos.view.tm.EggTM;
import lk.rangafarm.pos.view.tm.FoodTM;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EggFormController {
    public JFXComboBox cmbEggId;
    public JFXTextField txtEggQty;
    public TableView<EggTM> tblEgg;
    public JFXTextField txtBucketId;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public TableColumn colProductId;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public JFXTextField txtUpProductId;
    public JFXTextField txtUpEggDescription;
    public JFXTextField txtUpUnitPrice;
    public JFXTextField txtUpQtyOnHand;

    ProductBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.PRODUCT);
    EggBucketDetailBo eBo = BoFactory.getInstance().getBo(BoFactory.BoType.EGGBUCKETDETAIL);
    AddEggBo aBo = BoFactory.getInstance().getBo(BoFactory.BoType.ADDEGG);

    public void initialize(){
        loadEggId();
        loadAllEgg();
        setCellValueFactory();
        tableListener();
    }

    public void loadEggId(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> eggId = bo.getEggId();
            for(String s : eggId){
                obList.add(s);
            }
            cmbEggId.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory(){
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
    }

    private void tableListener(){
        tblEgg.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(EggTM tm) {
        try {
            txtUpProductId.setText(tm.getProductId());
            txtUpEggDescription.setText(tm.getDescription());
            txtUpUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
            txtUpQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));
        }catch (Exception e){

        }
    }

    public void loadAllEgg(){
        try {
            ObservableList<EggTM> obList = FXCollections.observableArrayList();
            List<ProductDto> dto = bo.getAllEgg();
            for(ProductDto d : dto) {
                obList.add(new EggTM(
                        d.getProductId(),
                        d.getDescription(),
                        d.getUnitPrice(),
                        d.getQtyOnHand()
                ));

            }
            tblEgg.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent event) {
        if(Pattern.compile("^(EB)[0-9]{1,50}$").matcher(txtBucketId.getText()).matches()){
            if(Pattern.compile("^[A-z | ]{1,50}$").matcher(txtDescription.getText()).matches()){
                if(Pattern.compile("^[0-9 | .]{1,}$").matcher(txtUnitPrice.getText()).matches()){
                    if(Pattern.compile("^[0-9]{1,}$").matcher(txtEggQty.getText()).matches()){
                        int eggQty = 0;
                        try {
                            ProductDto dto = bo.getProduct(String.valueOf(cmbEggId.getValue()));
                            eggQty = Integer.parseInt(txtEggQty.getText()) + dto.getQtyOnHand();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ProductDto pDto = new ProductDto(
                                String.valueOf(cmbEggId.getValue()),
                                txtDescription.getText(),
                                Double.parseDouble(txtUnitPrice.getText()),
                                Integer.parseInt(String.valueOf(eggQty)));

                        EggBucketDetailDto eDto = new EggBucketDetailDto(
                                txtBucketId.getText(),
                                String.valueOf(cmbEggId.getValue()),
                                Integer.parseInt(txtEggQty.getText())
                        );

                        try {
                            if(aBo.addEgg(pDto, eDto)){
                                new Alert(Alert.AlertType.INFORMATION, "Egg Added to Store Success!").show();
                                loadAllEgg();
                                txtEggQty.setText(null);
                                txtUnitPrice.setText(null);
                                txtDescription.setText(null);
                            }else{
                                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else{
                        txtEggQty.setFocusColor(Paint.valueOf("Red"));
                        txtEggQty.requestFocus();
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
            txtBucketId.setFocusColor(Paint.valueOf("Red"));
            txtBucketId.requestFocus();
        }

   }

    public void cmbEggIdOnAction(ActionEvent event) {
        try {
            ProductDto dto = bo.getProduct(String.valueOf(cmbEggId.getValue()));
            txtDescription.setText(dto.getDescription());
            txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent event) {
        if(Pattern.compile("^(EG)[0-9]{1,50}$").matcher(txtUpProductId.getText()).matches()){
            if(Pattern.compile("^[A-z | 0-9 | -]{1,50}$").matcher(txtUpEggDescription.getText()).matches()){
                if(Pattern.compile("^[0-9 | .]+$").matcher(txtUpUnitPrice.getText()).matches()){
                    if(Pattern.compile("^[0-9]+$").matcher(txtUpQtyOnHand.getText()).matches()){
                        try {
                            if(bo.UpdateProduct(new ProductDto(
                                    txtUpProductId.getText(),
                                    txtUpEggDescription.getText(),
                                    Double.parseDouble(txtUpUnitPrice.getText()),
                                    Integer.parseInt(txtUpQtyOnHand.getText())
                            ))){
                                new Alert(Alert.AlertType.INFORMATION,"Egg has Updated!").show();
                                loadAllEgg();

                                txtUpProductId.setText(null);
                                txtUpEggDescription.setText(null);
                                txtUpUnitPrice.setText(null);
                                txtUpQtyOnHand.setText(null);
                            }
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR,"Something went Wrong !").show();
                        }
                    }else{
                        txtUpQtyOnHand.setFocusColor(Paint.valueOf("Red"));
                        txtUpQtyOnHand.requestFocus();
                    }
                }else{
                    txtUpUnitPrice.setFocusColor(Paint.valueOf("Red"));
                    txtUpUnitPrice.requestFocus();
                }
            }else{
                txtUpEggDescription.setFocusColor(Paint.valueOf("Red"));
                txtUpEggDescription.requestFocus();
            }

        }else{
            txtUpProductId.setFocusColor(Paint.valueOf("Red"));
            txtUpProductId.requestFocus();
        }
    }
}
