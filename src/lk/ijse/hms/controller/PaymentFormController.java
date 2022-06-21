/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/21/2022 7:35 PM
 */
package lk.ijse.hms.controller;

import javafx.event.ActionEvent;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;

public class PaymentFormController {
    public void canselBtnOnAction(ActionEvent actionEvent) {
        try {
            Navigations.getInstance().closeStage(actionEvent);
            Navigations.getInstance().setNewStage("Main-Form");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
