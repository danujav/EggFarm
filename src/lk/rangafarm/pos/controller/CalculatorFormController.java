/*
package lk.rangafarm.pos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorFormController implements Initializable {
    @FXML
    private TextField txtDisplay;
    private int decimalClick = 0;
    private String generalOperationObject;
    private double firstDouble;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    public void handlerGeneralAction(javafx.event.ActionEvent event) {
        generalOperationObject = ((Button) event.getSource()).getText();
        switch (generalOperationObject) {
            case "AC":
                txtDisplay.setText("");
                decimalClick = 0;
                break;
            case "+/-":
                double plusMinus = Double.parseDouble(String.valueOf(txtDisplay.getText()));
                plusMinus = plusMinus * (-1);
                txtDisplay.setText(String.valueOf(plusMinus));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                String currentText = txtDisplay.getText();
                firstDouble = Double.parseDouble(currentText);
                txtDisplay.setText("");
                decimalClick = 0;
                break;
            default:
        }
    }

    public void handlerDigitAction(javafx.event.ActionEvent event) {
        String digitObject = ((Button) event.getSource()).getText();
        String oldText = txtDisplay.getText();
        String newText = oldText + digitObject;
        txtDisplay.setText(newText);
    }

    public void handlerDecimalAction(javafx.event.ActionEvent event) {
        if (decimalClick == 0) {
            String decimalObject = ((Button) event.getSource()).getText();
            String oldText = txtDisplay.getText();
            System.out.println(oldText);
            String newText = oldText + decimalObject;
            System.out.println(newText);
            txtDisplay.setText(newText);
            decimalClick = 1;
        }
    }

    public void handlerEqualAction(javafx.event.ActionEvent event) {
        double secondDouble;
        double result=0;
        String secondText = txtDisplay.getText();
        secondDouble = Double.parseDouble(secondText);

        switch (generalOperationObject) {
            case "+":
                result = firstDouble + secondDouble;
                break;
            case "-":
                result = firstDouble - secondDouble;
                break;
            case "*":
                result = firstDouble * secondDouble;
                break;
            case "/":
                result = firstDouble / secondDouble;
                break;
            default:
        }
        String format = String.format("%.1f", result);
        txtDisplay.setText(format);
    }
}
*/
