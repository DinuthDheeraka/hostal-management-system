/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/16/2022 7:00 PM
 */
package lk.ijse.hms.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NavigateUI {
    private double xOffset = 0;
    private double yOffset = 0;

    private static NavigateUI navigateUI;

    private NavigateUI(){}

    public static NavigateUI getNavigateUI(){
        return navigateUI==null? navigateUI = new NavigateUI() : navigateUI;
    }

    public void setNewStage(String location) throws IOException {
        Stage stage = new Stage();
        Scene scene = (new Scene(FXMLLoader.load(getClass().getResource
                ("/lk/ijse/hms/view/"+location+".fxml"))));
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }
        });

        stage.show();
    }

    public void closeCurrentStage(AnchorPane location){
        Stage stage =(Stage) location.getScene().getWindow();
        stage.close();
    }

    public void closeStage(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void closeStage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void minimizeStage(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void minimizeStage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void setNewParentToCurrentStage(String parentLocation, AnchorPane targetLocation) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../lk.ijse.pos.view/"+parentLocation+".fxml"));
        //targetLocation.getChildren().clear();
        targetLocation.getChildren().add(parent);
    }

    public void addParentToCurrentStage(String parentLocation,AnchorPane anchorPane) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/hms/view/"+parentLocation+".fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(parent);
    }

    public  void transparentUi(Stage stage, Scene scene){
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }
        });

        stage.show();
    }
}
