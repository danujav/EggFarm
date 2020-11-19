package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.AttendanceBo;
import lk.rangafarm.pos.bo.custom.EmployeeBo;
import lk.rangafarm.pos.dto.AttendanceDto;
import lk.rangafarm.pos.dto.EmployeeDto;
import lk.rangafarm.pos.view.tm.AttendanceTM;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AttendanceFormController {
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public LineChart chart;
    public JFXComboBox cmbEmployeeId;
    public JFXButton btnCheckAttendance;
    public JFXButton btnCheckIn;
    public JFXButton btnCheckOut;
    public TableView<AttendanceTM> tblAttendance;
    public JFXButton btnAbsent;
    public Label lblEmployeeName;
    public Label lblAbsentEmployees;
    public TableColumn colDate;
    public TableColumn colCheckIn;
    public TableColumn colCheckOut;
    public TableColumn colWorkingHour;

    EmployeeBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.EMPLOYEE);
    AttendanceBo aBo = BoFactory.getInstance().getBo(BoFactory.BoType.ATTENDANCE);

    public void initialize(){
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));*/

        loadEmployeeId();
        checkAbsentEmployee();
        setCellValueFactory();

    }

    public void loadEmployeeId(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> idList = bo.getEmployeeName();
            for(String s : idList){
                obList.add(s);
            }
            cmbEmployeeId.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbEmployeeIdOnAction(ActionEvent event) {
        try {
            EmployeeDto dto = bo.getEmployee(String.valueOf(cmbEmployeeId.getValue()));
            lblEmployeeName.setText(dto.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAbsentEmployee(){
        try {
            lblAbsentEmployees.setText(String.valueOf(aBo.checkAbsentEmployee()));
        } catch (Exception e) {
            lblAbsentEmployees.setText("0");
        }
    }

    private void setCellValueFactory(){
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory("checkIn"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory("checkOut"));
        colWorkingHour.setCellValueFactory(new PropertyValueFactory("workingHour"));
    }

    public void btnCheckAttendance(ActionEvent event) {
        ObservableList<AttendanceTM> obList = FXCollections.observableArrayList();
        try {
            List<AttendanceDto> allAttendance = aBo.getAllAttendance(String.valueOf(cmbEmployeeId.getValue()));
            for(AttendanceDto dto : allAttendance){
                obList.add(new AttendanceTM(
                        dto.getDates(),
                        dto.getCheckIn(),
                        dto.getCheckOut(),
                        dto.getWorkingHour()
                ));
                tblAttendance.setItems(obList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCheckIn(ActionEvent event) {
        String date = LocalDate.now().toString();// date eka ganna code eka


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm"); // line 3ma time eka ganna eka
        LocalDateTime now = LocalDateTime.now();
        String checkTime = dtf.format(now);

        try {
            if(aBo.saveAttendance(new AttendanceDto(
                String.valueOf(cmbEmployeeId.getValue()),
                date,
                checkTime,
                checkTime,
                checkTime
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "Good Morning").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something going wrong !").show();
        }
    }

    public void btnCheckOut(ActionEvent event) {
        String checkIn = "absent";

        String date = LocalDate.now().toString();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String checkOut = dtf.format(now);

        try {
            AttendanceDto attendance = aBo.getAttendance(String.valueOf(cmbEmployeeId.getValue()));
            checkIn = attendance.getCheckIn();

            if(aBo.updateAttendance(new AttendanceDto(
                    String.valueOf(cmbEmployeeId.getValue()),
                    date,
                    checkIn,
                    checkOut,
                    checkOut
            ))){
                new Alert(Alert.AlertType.CONFIRMATION, "Come Tomorrow !!").show();

            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong !!").show();
            }
        } catch (Exception e) {
            //new Alert(Alert.AlertType.ERROR, "Something going wrong !").show();
            System.out.println(e);
        }
    }

    public void btnAbsent(ActionEvent event) {
        String date = LocalDate.now().toString();
        try {
            if(aBo.saveAttendance(new AttendanceDto(
                    String.valueOf(cmbEmployeeId.getValue()),
                    date,
                    "Absent",
                    "Absent",
                    "Absent"
            ))){
                new Alert(Alert.AlertType.INFORMATION, "Absent Confirmed !").show();
                checkAbsentEmployee();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something going wrong !").show();
        }
    }
}
