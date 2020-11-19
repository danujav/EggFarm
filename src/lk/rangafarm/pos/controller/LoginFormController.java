package lk.rangafarm.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.rangafarm.pos.bo.BoFactory;
import lk.rangafarm.pos.bo.custom.UserBo;
import lk.rangafarm.pos.dto.UserDto;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class LoginFormController {
    public AnchorPane anchorPane;
    public RadioButton radioAcceptBtn;
    public JFXComboBox cmbRoleType;
    public JFXTextField txtEmailAddress;
    public JFXTextField txtName;
    public JFXTextField txtUserName;
   /* public JFXPasswordField txtLogPassword;
    public JFXTextField txtLogUserName;*/
    public AnchorPane mainRoot;
    public TextField txtLogUserName;
    public PasswordField txtLogPassword;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private AnchorPane root;

    UserBo uBo = BoFactory.getInstance().getBo(BoFactory.BoType.USER);

    public void left(javafx.event.ActionEvent event) {
        /*TranslateTransition translate = new TranslateTransition(Duration.seconds(2), anchorPane);
        translate.setToX(root.getLayoutX());
        translate.play();*/

         TranslateTransition translate = new TranslateTransition(Duration.seconds(2), anchorPane);
        translate.setToX(root.getLayoutX() - root.getLayoutX());
        translate.play();
        
    }

    public void right(javafx.event.ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), anchorPane);
        translate.setToX(anchorPane.getLayoutX() + (root.getPrefWidth() - anchorPane.getPrefWidth()));
        translate.play();
    }

    public void btnSignUpOnAction(ActionEvent event) {
        if(Pattern.compile("^[A-z |0-9]+$").matcher(txtUserName.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,50}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$").matcher(txtEmailAddress.getText()).matches()) {
                    if (Pattern.compile("^[A-z|0-9| -| & | @ | #]+$").matcher(txtPassword.getText()).matches()) {
                        if (Pattern.compile("^[A-z |0-9| @ |$ | # | &]{8,}$").matcher(txtPassword.getText()).matches()) {
                            if (radioAcceptBtn.isSelected()) {
                                if (txtPassword.getText().equals(txtRePassword.getText())) {
                                    try {
                                        if (uBo.signUp(new UserDto(
                                                txtUserName.getText(),
                                                txtName.getText(),
                                                txtEmailAddress.getText(),
                                                txtPassword.getText(),
                                                "Employee"
                                        ))) {
                                            left(event);
                                            txtUserName.setText(null);
                                            txtName.setText(null);
                                            txtEmailAddress.setText(null);
                                            txtPassword.setText(null);
                                            txtRePassword.setText(null);
                                            radioAcceptBtn.setText(null);
                                        }
                                    } catch (Exception e) {
                                        //  e.printStackTrace();
                                    }
                                } else {
                                    txtRePassword.setFocusColor(Paint.valueOf("Red"));
                                    txtRePassword.requestFocus();
                                }

                            } else {

                            }
                        } else {
                            txtPassword.setFocusColor(Paint.valueOf("Red"));
                            txtPassword.requestFocus();
                        }
                    } else {
                        txtPassword.setFocusColor(Paint.valueOf("Red"));
                        txtPassword.requestFocus();
                    }
                } else {
                    txtEmailAddress.setFocusColor(Paint.valueOf("Red"));
                    txtEmailAddress.requestFocus();
                }
            } else {
                txtName.setFocusColor(Paint.valueOf("Red"));
                txtName.requestFocus();
            }
        }else{
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
        }


    }

    public void termAndConditionOnAction(ActionEvent event) {

    }

    public void btnLogInOnAction(ActionEvent event) {
        try {
            UserDto dto = uBo.logIn(txtLogUserName.getText(), txtLogPassword.getText());
            if(dto != null){
                if(dto.getRoleType().equalsIgnoreCase("Owner")){
                    this.mainRoot.getChildren().clear();
                    this.mainRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml")));

                    Image img = new Image("lk/rangafarm/pos/asserts/loginNotify.png");
                    Notifications notifiBuilder = Notifications.create().title("Login")
                            .text("Successfully Login As Owner!")
                            .graphic(new ImageView(img))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                }
                            });
                    notifiBuilder.darkStyle();
                    notifiBuilder.show();

                }else{
                    this.mainRoot.getChildren().clear();
                    this.mainRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/DashBoardFormEmployee.fxml")));

                    Image img = new Image("lk/rangafarm/pos/asserts/loginNotify.png");
                    Notifications notifiBuilder = Notifications.create().title("Login")
                            .text("Successfully Login As Employee!")
                            .graphic(new ImageView(img))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {

                                }
                            });
                    notifiBuilder.darkStyle();
                    notifiBuilder.show();
                }
            }
        } catch (Exception e) {
            //txtLogUserName.setStyle("-fx-background-color: rgb(231, 76, 60);");
            txtLogPassword.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            txtLogPassword.requestFocus();
        }
    }

    @FXML
    void hyperLearnMoreOnAction(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("http://bing.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
