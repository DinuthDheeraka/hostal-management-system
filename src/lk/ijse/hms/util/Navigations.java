/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/18/2022 2:33 PM
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

public class Navigations {
    private static Navigations navigations;

    public double yOffset;
    public double xOffset;

    private Navigations(){}

    public static Navigations getInstance(){
        return navigations==null? navigations = new Navigations() : navigations;
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

    public void closeStage(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addParentToCurrentStage(String parentLocation, AnchorPane anchorPane) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/hms/view/"+parentLocation+".fxml"));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(parent);
    }
}
