package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.CustomerBo;
import lk.rangafarm.pos.bo.custom.OrderBo;
import lk.rangafarm.pos.bo.custom.ProductBo;
import lk.rangafarm.pos.bo.custom.SellingBo;
import lk.rangafarm.pos.db.DBConnection;
import lk.rangafarm.pos.dto.CustomerDto;
import lk.rangafarm.pos.dto.OrderDetailDto;
import lk.rangafarm.pos.dto.ProductDto;
import lk.rangafarm.pos.dto.SellingDto;
import lk.rangafarm.pos.view.tm.PlaceOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SellingFormController {
    public AnchorPane root;
    public JFXComboBox cmbCustomerId;
    public Label lblOrderId;
    public JFXComboBox cmbProductId;
    public TableView<PlaceOrderTM> tblOrder;
    public Label lblCustomerName;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public JFXTextField txtQty;
    public TextField txtUnitPrice;
    public TableColumn colProductId;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotalPrice;
    public TableColumn colOperator;
    public TextField txtNetTot;
    public double netTot = 0;
    public JFXTextField txtCash;
    public TextField txtExchangeCost;
    public int itemCount = 0;

    CustomerBo cBo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);
    ProductBo pBo = BoFactory.getInstance().getBo(BoFactory.BoType.PRODUCT);
    OrderBo oBo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER);
    ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();
    SellingBo sBo = BoFactory.getInstance().getBo(BoFactory.BoType.SELLING);

    public void initialize(){
        getCustomerId();
        getProductId();
        getNextOId();
        setCellValueFactory();
    }

    private void setCellValueFactory(){
        colProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        colOperator.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    public void btnCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/CustomerForm.fxml")));
    }

    public void getCustomerId(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> name = cBo.getCustomerId();
            for(String s : name){
                obList.add(s);
            }
            cmbCustomerId.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getProductId(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            ArrayList<String> eggId = pBo.getProductId();

            for(String s : eggId){
                obList.add(s);
            }
            cmbProductId.setItems(obList);
        } catch (Exception e) {
           // e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "oops! cannot load Product Ids");
        }
    }

    public void cmbCustomerIdOnAction(ActionEvent event) {
        try {
            CustomerDto dto = cBo.getCustomer(String.valueOf(cmbCustomerId.getValue()));
            lblCustomerName.setText(dto.getName());
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    public void cmbProductIdOnAction(ActionEvent event) {
        try {
            ProductDto dto = pBo.getProduct(String.valueOf(cmbProductId.getValue()));
            txtDescription.setText(dto.getDescription());
            txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void getNextOId(){
        try {
            lblOrderId.setText(oBo.getNextOrderId());
        } catch (Exception e) {
            //e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Wrong").show();
        }
    }

    public void getExchangeCostOnAction(ActionEvent event) {
        try {
            double netTot = Double.parseDouble(txtNetTot.getText());
            double cash = Double.parseDouble(txtCash.getText());

            double exchangeCost = cash - netTot;

            txtExchangeCost.setText("-" + exchangeCost);

        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, "Input Cash Amount!").show();
        }
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]+$").matcher(txtQty.getText()).matches()) {
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
            int qty2 = Integer.parseInt(txtQty.getText());
            String code = String.valueOf(cmbProductId.getValue());
            String desc = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            if (qty <= 0) {
                new Alert(Alert.AlertType.WARNING, "Please Enter the valid Quantity !").show();
                return;
            }

            if (qtyOnHand < qty2) {
                new Alert(Alert.AlertType.WARNING, "Not Sufficient Quantity in the store !").show();

            } else {

                Button btn = new Button("Delete");

                try {
                    if (!obList.isEmpty()) { // check observableList is empty
                        for (int i = 0; i < tblOrder.getItems().size(); i++) { // check all rows in table
                            if (colProductId.getCellData(i).equals(code)) { // check  itemcode in table == itemcode we enter
                                int temp = (int) colQty.getCellData(i); // get qty in table for temp
                                temp += qty; // add new qty to old qty

                                if (temp > qtyOnHand) {
                                    new Alert(Alert.AlertType.WARNING, "Not Sufficient Quantity in the store !").show();
                                    return;
                                }
                                double tot = temp * unitPrice; // get new total
                                obList.get(i).setQty(temp); // set new qty to observableList
                                obList.get(i).setTotalPrice(tot); // set new total to observableList
                                tblOrder.refresh(); // refresh table

                                netTot += qty * unitPrice;
                                txtNetTot.setText(String.valueOf(netTot));
                                txtQty.setText(null);

                                return;
                            }
                        }
                    }
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
                                PlaceOrderTM tm = tblOrder.getSelectionModel().getSelectedItem();

                                netTot = Double.parseDouble(txtNetTot.getText());
                                netTot = netTot - tm.getTotalPrice();
                                txtNetTot.setText(String.valueOf(netTot));
                                itemCount--;

                                tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItem());


                            } else {

                            }
                        } catch (Exception e1) {
                            new Alert(Alert.AlertType.ERROR, "oops !! something went wrong").show();
                        }
                    });

                    netTot += qty * unitPrice;
                    txtNetTot.setText(String.valueOf(netTot));
                    itemCount++;
                    obList.add(new PlaceOrderTM(code, desc, unitPrice, qty, (qty * unitPrice), btn));

                    tblOrder.setItems(obList); // if their is no list or, no matched itemcode
                    txtQty.setText(null);
                } catch (Exception e) {
                    new Alert(Alert.AlertType.WARNING, "Please input Quantity !").show();
                }
            }
        }else{
            txtQty.setFocusColor(Paint.valueOf("Red"));
            txtQty.requestFocus();
        }
    }

    public void btnPayOnAction(ActionEvent event) {
        String customerName = lblCustomerName.getText();
        String exchange = txtExchangeCost.getText();
        if(customerName == null && exchange == null){
            txtCash.setFocusColor(Paint.valueOf("Red"));
           // txtCash.requestFocus();
            return;
        }
        String orderId = lblOrderId.getText();
        String custId = String.valueOf(cmbCustomerId.getValue());
        String date = LocalDate.now().toString();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));

        ArrayList<OrderDetailDto> orderDetailList = new ArrayList();

        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            PlaceOrderTM tm = obList.get(i);
            orderDetailList.add(new OrderDetailDto(
                    orderId,
                    tm.getProductId(),
                    tm.getDescription(),
                    tm.getUnitPrice(),
                    tm.getQty()
            ));
        }

        try {
            if(sBo.PlaceOrder(new SellingDto(
               orderId,
               custId,
               date,
               time,
               orderDetailList
            ))){
                //new Alert(Alert.AlertType.CONFIRMATION, "Order Success !").show();
                ButtonType yes = new ButtonType("Yes",
                        ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO",
                        ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Order Success!!\nDo you want print a Bill ?", yes, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == yes){
                    try {
                        InputStream is = this.getClass().getResourceAsStream("/lk/rangafarm/pos/report/Bill.jrxml");
                        JasperReport jr = JasperCompileManager.compileReport(is);


                        HashMap hm = new HashMap();
                        hm.put("orderId", lblOrderId.getText());  // Jasper Report eke dila tyena parameter wala name tikamai methana key eka wdyata danne
                        hm.put("netTot", txtNetTot.getText());
                        hm.put("cash", txtCash.getText());
                        hm.put("exchangingCost", txtExchangeCost.getText());
                        hm.put("itemCount", String.valueOf(itemCount));

                        JasperPrint jp = JasperFillManager.fillReport(jr,hm, DBConnection.getInstance().getConnection());
                        //  JasperPrintManager.printReport(jp, true);
                        JasperViewer.viewReport(jp, false);
                        txtExchangeCost.setText(null);
                        txtCash.setText(null);
                        txtNetTot.setText(null);

                    } catch (JRException e) {
                        e.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }


                getNextOId();
                lblCustomerName.setText(null);
                cmbCustomerId.setValue(null);
                tblOrder.getItems().clear();
                txtDescription.setText(null);
                txtUnitPrice.setText(null);
                txtQtyOnHand.setText(null);
                cmbProductId.setValue(null);
                txtExchangeCost.setText(null);
                txtCash.setText(null);
                txtNetTot.setText(null);


            }
        } catch (Exception e) {
            //e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "oops! Something went wrong").show();
        }


    }

    public void btnPrintBillOnAction(ActionEvent event) {
        /*try {
            InputStream is = this.getClass().getResourceAsStream("/lk/rangafarm/pos/report/Bill.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);


            HashMap hm = new HashMap();
            hm.put("orderId", lblOrderId.getText());  // Jasper Report eke dila tyena parameter wala name tikamai methana key eka wdyata danne
            hm.put("netTot", txtNetTot.getText());
            hm.put("cash", txtCash.getText());
            hm.put("exchangingCost", txtExchangeCost.getText());
            hm.put("itemCount", String.valueOf(itemCount));

            JasperPrint jp = JasperFillManager.fillReport(jr,hm, DBConnection.getInstance().getConnection());
            //  JasperPrintManager.printReport(jp, true);
            JasperViewer.viewReport(jp, false);
            txtExchangeCost.setText(null);
            txtCash.setText(null);
            txtNetTot.setText(null);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
