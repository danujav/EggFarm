package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.EggBucketBo;
import lk.rangafarm.pos.bo.custom.EmployeeBo;
import lk.rangafarm.pos.dto.EggBucketDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.entity.EggBucket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EggCleanFormController {
    public JFXComboBox cmbTime;
    public JFXTextField txtBucketId;
    public JFXTextField txtEggQty;
    public JFXComboBox cmbEmpName;
    public JFXComboBox cmbCage;
    public Label lblCageName;
    public Label lblEmployeeName;
    public Label lblBucketId;
    public Label lblTime;
    public Label lblQty;
    public Label lblTotalForToday;
    public Label lblFourEgg;
    public Label lblTwelveEgg;
    public Label lblTwoEgg;
    public Label lblTenEgg;
    public Label lblSevenEgg;


    EggBucketBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.EGGBUCKET);
    EmployeeBo eBo = BoFactory.getInstance().getBo(BoFactory.BoType.EMPLOYEE);

    public void initialize() {
        addTime();
        getCageName();
        getEmployeeName();
        getId();
        recentlyAdded();
        totalEggForToday();
        sevenEgg();
        tenEgg();
        twelveEgg();
        twoEgg();
        fourEgg();
    }

    public void sevenEgg(){
        try {
            lblSevenEgg.setText(String.valueOf(bo.sevenEgg()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void twoEgg(){
        try {
            lblTwoEgg.setText(String.valueOf(bo.twoEgg()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tenEgg(){
        try {
            lblTenEgg.setText(String.valueOf(bo.tenEgg()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void twelveEgg(){
        try {
            lblTwelveEgg.setText(String.valueOf(bo.twelveEgg()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fourEgg(){
        try {
            lblFourEgg.setText(String.valueOf(bo.fourEgg()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalEggForToday(){
        try {
            String date = LocalDate.now().toString();
            int i = bo.totalEggForToday(date);
            lblTotalForToday.setText(String.valueOf(i));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getId(){
        try {
            String id = bo.getId();
            txtBucketId.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recentlyAdded()  {
        try {
            EggBucketDto dto = bo.recentlyAdded();
            EmployeeDto employee = eBo.getEmployee(dto.getEmpId());

            lblCageName.setText(dto.getCageId());
            lblEmployeeName.setText(employee.getName());
            lblBucketId.setText(dto.getEggBucketId());
            lblTime.setText(dto.getTimes());
            lblQty.setText(String.valueOf(dto.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getEmployeeName(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> allEmpId = bo.getEmployeeId();
            for(String s : allEmpId){
                obList.add(s);
            }
            cmbEmpName.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCageName(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> allCageNames = bo.getCageNames();
            for(String s : allCageNames){
                obList.add(s);
            }
            cmbCage.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTime(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("7:30 am");
        obList.add("10:30 am");
        obList.add("12:30 am");
        obList.add("2:30 am");
        obList.add("4:30 am");
        cmbTime.setItems(obList);
    }

    public void btnKeepOnAction(ActionEvent event) {
        String s = LocalDate.now().toString();

        if(Pattern.compile("^(EB)[0-9]{1,50}$").matcher(txtBucketId.getText()).matches()){
            if(Pattern.compile("^[0-9]{1,50}$").matcher(txtEggQty.getText()).matches()){
                if(cmbTime.getValue() == null){
                    new Alert(Alert.AlertType.WARNING, "Please make sure all are fine !").show();
                }else if(txtEggQty.getText() == null){
                    new Alert(Alert.AlertType.WARNING, "Please make sure all are fine !").show();
                }else {
                    try {
                        if (bo.saveEggBucket(new EggBucketDto(
                                txtBucketId.getText(),
                                String.valueOf(cmbCage.getValue()),
                                String.valueOf(cmbEmpName.getValue()),
                                s,
                                String.valueOf(cmbTime.getValue()),
                                Integer.parseInt(txtEggQty.getText())
                        ))) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Kept!").show();
                            getId();
                            recentlyAdded();
                            totalEggForToday();
                            sevenEgg();
                            tenEgg();
                            twelveEgg();
                            twoEgg();
                            fourEgg();

                            txtEggQty.setText(null);
                            cmbTime.setValue(null);
                            cmbEmpName.setValue(null);
                            cmbCage.setValue(null);
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING, "Please make sure all are fine !").show();
                    }
                }
            }else{
                txtEggQty.setFocusColor(Paint.valueOf("Red"));
                txtEggQty.requestFocus();
            }

        }else{
            txtBucketId.setFocusColor(Paint.valueOf("Red"));
            txtBucketId.requestFocus();
        }
    }
}
