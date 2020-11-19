package lk.rangafarm.pos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/DashBoardForm.fxml"))));
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"))));
       // primaryStage.setTitle("Ranga Egg Farm");
       // primaryStage.initStyle(StageStyle.UNDECORATED);
       // primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}

/*package lk.rangafarm.pos.controller;

        import javafx.event.ActionEvent;
        import javafx.scene.Node;
        import javafx.scene.input.MouseEvent;
        import javafx.stage.Stage;

public class DashBordFormController {
    double x;
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
    }

    public void button(ActionEvent actionEvent) {
    }
}*/
