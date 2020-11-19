package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.CageBo;
import lk.rangafarm.pos.dto.CageDto;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.view.tm.CageTM;
import lk.rangafarm.pos.view.tm.CustomerTM;

import java.util.List;
import java.util.regex.Pattern;

public class CageFormController {

    public JFXTextField txtCageId;
    public JFXTextField txtNumOfHens;
    public JFXTextField txtQtyOnFood;
    public JFXTextField txtQtyOnVitamin;
    public JFXTextField txtUpNumOfHens;
    public JFXTextField txtUpCageId;
    public JFXTextField txtUpQtyOnFood;
    public JFXTextField txtUpQtyOnVitamin;
    public TableView<CageTM> tblCage;
    public TableColumn colCageId;
    public TableColumn colNumOfHens;
    public TableColumn colQtyOnFood;
    public TableColumn colQtyOnVitamin;
    public Label lblTotHens;

    CageBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);

    public void initialize(){
        getId();
        loadAllCage();
        setCellValueFactory();
        tableListener();
        getTotHens();
    }

    public void getTotHens(){
        try {
            lblTotHens.setText(String.valueOf(bo.getHensCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableListener(){
        tblCage.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(CageTM tm) {
        try {
            txtUpCageId.setText(tm.getCageId());
            txtUpNumOfHens.setText(String.valueOf(tm.getNumOfHens()));
            txtUpQtyOnFood.setText(tm.getQtyOnFood());
            txtUpQtyOnVitamin.setText(tm.getQtyOnVitamin());
        }catch (Exception e){

        }
    }

    private void setCellValueFactory(){
        colCageId.setCellValueFactory(new PropertyValueFactory("cageId"));
        colNumOfHens.setCellValueFactory(new PropertyValueFactory("numOfHens"));
        colQtyOnFood.setCellValueFactory(new PropertyValueFactory("qtyOnFood"));
        colQtyOnVitamin.setCellValueFactory(new PropertyValueFactory("qtyOnVitamin"));
    }
    
    public void getId(){
        try {
            String id = bo.getId();
            txtCageId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllCage(){
        try {
            ObservableList<CageTM> tmList = FXCollections.observableArrayList();
            List<CageDto> allCage = bo.getAllCage();

            for(CageDto dto : allCage){
                tmList.add(new CageTM(
                        dto.getCageId(),
                        dto.getNoOfHens(),
                        dto.getQtyOnFood(),
                        dto.getQtyOnVitamin()
                ));
            }
            tblCage.setItems(tmList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnBuildOnAction(ActionEvent event) {
        if(Pattern.compile("^(CG00)[1-9]{1,50}$").matcher(txtCageId.getText()).matches()){
            if(Pattern.compile("^[0-9]{1,50}$").matcher(txtNumOfHens.getText()).matches()){
                if(Pattern.compile("^[A-z | () | - | 0-9]{1,50}$").matcher(txtQtyOnFood.getText()).matches()){
                    if(Pattern.compile("^[A-z | () | - | 0-9]{1,50}$").matcher(txtQtyOnVitamin.getText()).matches()){
                            try {
                                if(bo.saveCage(
                                        new CageDto(
                                                txtCageId.getText(),
                                                Integer.parseInt(txtNumOfHens.getText()),
                                                txtQtyOnFood.getText(),
                                                txtQtyOnVitamin.getText()
                                        )
                                )){
                                    txtCageId.setText(null);
                                    txtNumOfHens.setText(null);
                                    txtQtyOnFood.setText(null);
                                    txtQtyOnVitamin.setText(null);
                                    //loadAllCustomer();
                                    getId();
                                    getTotHens();
                                    new Alert(Alert.AlertType.CONFIRMATION,"Cage Build Success!").show();
                                }
                            } catch (Exception e) {
                                new Alert(Alert.AlertType.WARNING,"Cage Not Built!").show();
                            }
                    }else{
                        txtQtyOnVitamin.setFocusColor(Paint.valueOf("Red"));
                        txtQtyOnVitamin.requestFocus();
                    }
                }else{
                    txtQtyOnFood.setFocusColor(Paint.valueOf("Red"));
                    txtQtyOnFood.requestFocus();
                }
            }else{
                txtNumOfHens.setFocusColor(Paint.valueOf("Red"));
                txtNumOfHens.requestFocus();
            }

        }else{
            txtCageId.setFocusColor(Paint.valueOf("Red"));
            txtCageId.requestFocus();
        }
        //loadId();
    }

    public void btnUpdate(ActionEvent event) {
        if(Pattern.compile("^(CG00)[1-9]{1,50}$").matcher(txtUpCageId.getText()).matches()){
            if(Pattern.compile("^[0-9]{1,50}$").matcher(txtUpNumOfHens.getText()).matches()){
                if(Pattern.compile("^[A-z | () | - | 0-9]{1,50}$").matcher(txtUpQtyOnFood.getText()).matches()){
                    if(Pattern.compile("^[A-z | () | - | 0-9]{1,50}$").matcher(txtUpQtyOnVitamin.getText()).matches()){
                            try {
                                if(bo.UpdateCage(new CageDto(
                                        txtUpCageId.getText(),
                                        Integer.parseInt(txtUpNumOfHens.getText()),
                                        txtUpQtyOnFood.getText(),
                                        txtUpQtyOnVitamin.getText()
                                ))){
                                    loadAllCage();
                                    new Alert(Alert.AlertType.CONFIRMATION,"Cage has Updated!").show();
                                    txtUpCageId.setText(null);
                                    txtUpNumOfHens.setText(null);
                                    txtUpQtyOnFood.setText(null);
                                    txtUpQtyOnVitamin.setText(null);
                                    getTotHens();
                                }
                            } catch (Exception e) {
                                /*new Alert(Alert.AlertType.WARNING,"Something went Wrong !").show();*/
                                System.out.println(e);
                            }
                    }else{
                        txtUpQtyOnVitamin.setFocusColor(Paint.valueOf("Red"));
                        txtUpQtyOnVitamin.requestFocus();
                    }
                }else{
                    txtUpQtyOnFood.setFocusColor(Paint.valueOf("Red"));
                    txtUpQtyOnFood.requestFocus();
                }
            }else{
                txtUpNumOfHens.setFocusColor(Paint.valueOf("Red"));
                txtUpNumOfHens.requestFocus();
            }

        }else{
            txtUpCageId.setFocusColor(Paint.valueOf("Red"));
            txtUpCageId.requestFocus();
        }
    }
}
