/**
 * @author : Dinuth Dheeraka
 * Project Name: hostal-management-system
 * Created     : 6/16/2022 1:34 PM
 */
package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.SystemUserBO;
import lk.ijse.hms.dto.SystemUserDTO;
import lk.ijse.hms.util.IdsGenerator;
import lk.ijse.hms.util.Navigations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXTextField txtNewUserName;
    public JFXPasswordField txtNewPassword;
    //DI
    SystemUserBO systemUserBO = (SystemUserBO) BOFactory.getInstance().getBO(BOFactory.BOType.SYSTEM_USER);

    private String lastUserId;

    public LoginFormController() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lastUserId = IdsGenerator.generateId("US-",systemUserBO.getLastUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelBtnOnAction(ActionEvent actionEvent) {
        Navigations.getInstance().closeStage(actionEvent);
    }

    public void loginBtnOnAction(ActionEvent actionEvent) {
        try {
            if(systemUserBO.getSystemUserByUserNameAndPassword(txtUserName.getText(),txtPassword.getText())){
                Navigations.getInstance().closeStage(actionEvent);
                Navigations.getInstance().setNewStage("Main-Form");
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid Username or Password!").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sgnpLoginBtnOnAction(ActionEvent actionEvent) {
        try {
            try {
                //Add New System User
                systemUserBO.addSystemUser(new SystemUserDTO(lastUserId,txtNewUserName.getText(),txtNewPassword.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }

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
