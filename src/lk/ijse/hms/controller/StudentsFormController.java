/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/17/2022 5:58 PM
 */
package lk.ijse.hms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lk.ijse.hms.util.NavigateUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentsFormController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToHomeBtnOnAction(ActionEvent actionEvent) {
        try {
            NavigateUI.getNavigateUI().closeStage(actionEvent);
            NavigateUI.getNavigateUI().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
