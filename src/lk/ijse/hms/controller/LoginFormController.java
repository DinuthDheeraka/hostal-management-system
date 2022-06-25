/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/16/2022 1:34 PM
 */
package lk.ijse.hms.controller;

import javafx.event.ActionEvent;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;

public class LoginFormController {
    public void cancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void loginBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sgnpLoginBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sgnupCancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }
}
