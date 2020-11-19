package lk.rangafarm.pos.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

import javafx.util.Duration;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.CageBo;
import lk.rangafarm.pos.bo.custom.CustomerBo;
import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.entity.Product;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DashBordFormEmployeeController {
    public Label lblDate;
    public Label lblTime;
    public LineChart chart;
    public CategoryAxis x;
    public NumberAxis y;
    public AnchorPane root;
    public AnchorPane mainRoot;
    public Label lblFormName;
    public Label lblTotCustomer;
    public Label lblTotHens;
    public Label lblTotEggs;

    CustomerBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);
    CageBo cBo = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    ProductBo pBo = BoFactory.getInstance().getBo(BoFactory.BoType.PRODUCT);

    public void initialize() {
        generateDateTime();
        setChart();
        getCustomerCount();
        hensCount();
        getEggCount();
    }

    private void setChart(){
        try {
            XYChart.Series s = new XYChart.Series<>();
            s.setName("Egg");

            ArrayList<Integer> qty = pBo.getOrderWiseEggSellingRate();

            s.getData().add(new XYChart.Data<>("Small", qty.get(0)));
            s.getData().add(new XYChart.Data<>("Medium", qty.get(1)));
            s.getData().add(new XYChart.Data<>("Large", qty.get(2)));
            s.getData().add(new XYChart.Data<>("Xtra Large", qty.get(3)));
            s.getData().add(new XYChart.Data<>("Damage", qty.get(4)));

            chart.getData().addAll(s);

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }

    }

    public void getEggCount(){
        try {
            lblTotEggs.setText(String.valueOf(pBo.getEggCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCustomerCount(){
        try {
            lblTotCustomer.setText(String.valueOf(bo.getCustomerCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hensCount(){
        try {
            lblTotHens.setText(String.valueOf(cBo.getHensCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateDateTime() {
        //lblDate.setText(LocalDate.now().toString());

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Date date = Calendar.getInstance().getTime(); // OR new Date()
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        String formatStr = dateFormat.format(date);

        lblDate.setText(formatStr);
    }

    public void btnEggCleanOnAction(ActionEvent actionEvent) throws IOException {
        /*final Rectangle rect1 = new Rectangle(10, 10, 100, 100);
        rect1.setArcHeight(20);
        rect1.setArcWidth(20);
        rect1.setFill(Color.RED);
//...
        FadeTransition ft = new FadeTransition(Duration.millis(3000), rect1);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();*/

       /* final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);*/
//...
       /* Path path = new Path();
        path.getElements().add(new MoveTo(20,20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();*/

        initUi("EggCleanForm.fxml");
        lblFormName.setText("Dashboard -> Egg Clean");
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        this.mainRoot.getChildren().clear();
        this.mainRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/DashBoardFormEmployee.fxml")));
    }

    public void btnSellingOnAction(ActionEvent actionEvent) throws IOException {
        initUi("SellingForm.fxml");
        lblFormName.setText("Dashboard -> Selling");
    }

    public void btnAddEggOnAction(ActionEvent actionEvent) throws IOException {
        /*initUi("EggForm.fxml");
        lblFormName.setText("Dashboard -> Eggs");*/
    }

    public void btnAddFoodOnAction(ActionEvent actionEvent) throws IOException {
       /* initUi("FoodForm.fxml");
        lblFormName.setText("Dashboard -> Food");*/
    }

    public void btnCageOnAction(ActionEvent actionEvent) throws IOException {
        /*initUi("CageForm.fxml");
        lblFormName.setText("Dashboard -> Cage");*/
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        /*initUi("CustomerForm.fxml");
        lblFormName.setText("Dashboard -> Customer");*/
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        /*initUi("LoginForm.fxml");
        lblFormName.setText("Dashboard -> Reports");*/
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        /*initUi("EmployeeForm.fxml");
        lblFormName.setText("Dashboard -> Employees");*/
    }

    public void btnAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        initUi("AttendanceForm.fxml");
        lblFormName.setText("Dashboard -> Attendance");
    }

    private void initUi(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/" + location)));
    }

    public void calcOnAction(ActionEvent event) {
        Runtime rTime = Runtime.getRuntime();
        try {
            rTime.exec("gnome-calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnLogOutOnAction(ActionEvent event) throws IOException {
        this.mainRoot.getChildren().clear();
        this.mainRoot.getChildren().add(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml")));
    }
}































    /*double x;
    double y;
    public void exitOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void mousePressOnAction(MouseEvent mouseEvent) {
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    public void mouseDraggedOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() -x);
        stage.setY(mouseEvent.getScreenY() - y);

    }

    public void max(MouseEvent mouseEvent) {
        Stage s = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        s.setFullScreen(true);
    }

    public void minToolBar(MouseEvent mouseEvent) {
        Stage s = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        s.setIconified(true);
    }*/



